package com.crud.app.services;


import com.crud.app.model.Admin;
import com.crud.app.model.Appointment;
import com.crud.app.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MailService mailService;

    public List<Appointment> listAll(){
        return appointmentRepository.findAll();
    }

    public Appointment get(int id){
        return appointmentRepository.findById(id).get();

    }

    public void save(Appointment appointment) {
        try {
            Appointment savedAppointment = appointmentRepository.save(appointment);
            // Send emails after saving
            sendAppointmentEmails(savedAppointment);
        } catch (Exception e) { // Catching generic exceptions for now
            e.printStackTrace(); // Log the exception
            System.out.println("Error while sending email: " + e.getMessage());
        }
    }

    public Appointment update(Appointment appointment) throws MessagingException {
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        // Send emails after updating
        sendAppointmentEmails(updatedAppointment);
        return updatedAppointment;
    }

    public void delete(int id){
        appointmentRepository.deleteById(id);
    }

    private void sendAppointmentEmails(Appointment appointment) {
        try {
            System.out.println("Sending email to: " + appointment.getPatient().getEmail());
            // Actual email-sending logic here
            String patientSubject = "Appointment Confirmation";
            String patientBody = "Dear " + appointment.getPatient().getPatientName() +
                    ",\n\nYour appointment with Dr. " + appointment.getDoctor().getDoctorName() +
                    " is confirmed for " + appointment.getAppointmentDate() +
                    ".\n\nThank you for using our appointment service.";
            mailService.sendEmail(appointment.getPatient().getEmail(), patientSubject, patientBody);

            // Email to the doctor
            String doctorSubject = "New Appointment Notification";
            String doctorBody = "Dear Dr. " + appointment.getDoctor().getDoctorName() +
                    ",\n\nYou have a new appointment with patient " + appointment.getPatient().getPatientName() +
                    " scheduled for " + appointment.getAppointmentDate() +
                    ".\n\nPlease check your schedule for further details.";
            mailService.sendEmail(appointment.getDoctor().getEmail(), doctorSubject, doctorBody);
            if (appointment.getPatient().getEmail() == null || appointment.getDoctor().getEmail() == null) {
                System.out.println("Missing email for patient or doctor.");
            }
        } catch (Exception e) {
            System.out.println("Error while sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
