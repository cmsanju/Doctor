import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Appointment } from 'src/app/model/appointment';
import { AppointmentService } from 'src/app/service/appointment.service';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  appointments: Appointment[];

  isdoctor:boolean=false;
  ispatient:boolean=false;
  isadmin:boolean=false;

  constructor(private appointmentService: AppointmentService, private router: Router) {
    let role:string|null = localStorage.getItem("role");
    if(role!==null){
      role = role.trim().toLowerCase();
    } 
    if(role==="doctor"){
      this.isdoctor=true;
    }
    else if(role==="admin"){
      this.isadmin=true;
    }
    else{
      this.ispatient=true;
    }
   }

  

  ngOnInit(): void {
    this.getAppointments();
  }

  getAppointments(){
    this.appointmentService.getAppointmentList().subscribe(data => {
      this.appointments = data;
    });
  }

  updateAppointment(id: number){
    console.log(id);
    this.router.navigate(['/landing-page/update-appointment', id]);
  }



  deleteAppointment(id: number){
    // console.log(id);
    // this.router.navigate(['/landing-page/delete-user', id]);
    this.appointmentService.deleteAppointment(id).subscribe(
      data => {
        console.log(data);
        this.getAppointments();
      }, error => {
        console.log("Not Delete")
      }
    )

}
}
