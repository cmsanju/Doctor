import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {
  isdoctor:boolean=false;
  ispatient:boolean=false;
  isadmin:boolean=false;
  Role:string="unknown";
  constructor(private router: Router) {
    let role:string|null = localStorage.getItem("role");
    if(role!==null){
      role = role.trim().toLowerCase();
    } 
    if(role==="doctor"){
      this.isdoctor=true;
      this.Role="Doctor";
    }
    else if(role==="admin"){
      this.isadmin=true;
      this.Role="Admin";
    }
    else{
      this.ispatient=true;
      this.Role="User";
    }
   }

  ngOnInit(): void {
  }

  logout(){

    localStorage.removeItem('userid');
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    localStorage.removeItem('role');

    this.router.navigate(['/login']);
  }

  @ViewChild('sidebar') sidebar!: ElementRef;
  @ViewChild('toggle') toggle!: ElementRef;

  ngAfterViewInit(): void {
    this.toggle.nativeElement.addEventListener("click", () => {
      this.sidebar.nativeElement.classList.toggle("close");
    });
    
  }

}
