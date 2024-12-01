
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Doctor } from 'src/app/model/doctor';
import { DoctorService } from 'src/app/service/doctor.service';

@Component({
  selector: 'app-create-doctor',
  templateUrl: './create-doctor.component.html',
  styleUrls: ['./create-doctor.component.css']
})
export class CreateDoctorComponent implements OnInit {

  doctor: Doctor = new Doctor();
  isdoctor:boolean=false;
  ispatient:boolean=false;
  isadmin:boolean=false;

  constructor(private doctorService: DoctorService, private router: Router) {
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
  }

  saveUser(){
    this.doctorService.createDoctor(this.doctor).subscribe(data=>{
      console.log(data);
      this.goToUserList();
    }, error => console.log(error));
  }

  goToUserList(){
    this.router.navigate(['/landing-page/doctor-list']);
  }

}
