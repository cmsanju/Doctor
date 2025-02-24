import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from 'src/app/model/appointment';
import { AppointmentService } from 'src/app/service/appointment.service';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css']
})
export class UpdateAppointmentComponent implements OnInit {

  id: number;
  appointment: Appointment = new Appointment();
  loading: boolean = false;

  constructor(private appointmentService: AppointmentService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.appointmentService.getAppointmentById(this.id).subscribe(
      data => {
        this.appointment = data;
      }, error => console.log(error)
    );
  }

  saveAppointment() {
    // Set loading to true when the form is submitted
    this.loading = true;

    this.appointmentService.updateAppointment(this.id, this.appointment).subscribe(data => {
      this.loading = false;  // Set loading to false after the appointment is saved
      this.goToAppointmentList();
    }, error => {
      console.log(error);
      this.loading = false;  // In case of error, set loading to false
    });
  }

  goToAppointmentList() {
    this.router.navigate(['/landing-page/appointment-list']);
  }
}
