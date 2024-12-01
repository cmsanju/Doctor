package com.crud.app.controller;


import com.crud.app.model.Admin;
import com.crud.app.model.Appointment;
import com.crud.app.services.AppointmentService;
import com.crud.app.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.NoSuchElementException;
import javax.mail.MessagingException;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MailService mailService;

    @GetMapping("appointment")
    public List<Appointment> list(){
        return appointmentService.listAll();
    }


    @GetMapping("appointment/{id}")
    public ResponseEntity<Appointment> get(@PathVariable Integer id){
        try{
            Appointment appointment  = appointmentService.get(id);
            return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);

        } catch (NoSuchElementException e){
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("appointment")
    public ResponseEntity<?> add(@RequestBody Appointment appointment) {
        try {
            appointmentService.save(appointment); // Save and send email
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        } catch (Exception e) { // Catch generic exceptions if no specific one is propagated
            e.printStackTrace(); // Log the stack trace for debugging
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("appointment")
    public ResponseEntity<?> update(@RequestBody Appointment appointment) {
        try {
            appointmentService.update(appointment);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating appointment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("appointment/{id}")
    public void delete(@PathVariable Integer id) {
        appointmentService.delete(id);
    }

}
