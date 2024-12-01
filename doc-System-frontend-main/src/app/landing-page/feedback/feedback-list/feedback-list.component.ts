import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/model/feedback';
import { FeedbackService } from 'src/app/service/feedback.service';

@Component({
  selector: 'app-feedback-list',
  templateUrl: './feedback-list.component.html',
  styleUrls: ['./feedback-list.component.css']
})
export class FeedbackListComponent implements OnInit {

  feedbacks: Feedback[];

  isdoctor:boolean=false;
  ispatient:boolean=false;
  isadmin:boolean=false;
  userid:number=0;
  constructor(private feedbackService: FeedbackService, private router: Router) { 
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
    this.getFeedbacks();
  }

  getFeedbacks(){
    this.feedbackService.getFeedbackList().subscribe(data => {
      this.feedbacks = data;
    });
  }

  updateFeedback(id: number){
    console.log(id);
    this.router.navigate(['/landing-page/update-feedback', id]);
  }



  deleteFeedback(id: number){
    // console.log(id);
    // this.router.navigate(['/landing-page/delete-user', id]);
    this.feedbackService.deleteFeedback(id).subscribe(
      data => {
        console.log(data);
        this.getFeedbacks();
      }, error => {
        console.log("Not Delete")
      }
    )

} 

}
