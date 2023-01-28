import {Component, Input, OnInit} from '@angular/core';
import {Observable, Subscription} from "rxjs";
import {PatientService} from "../../../service/patient.service";
import {DiabeteService} from "../../../service/diabete.service";
import {Note} from "../../../entity/note";

@Component({
  selector: 'app-diabete',
  templateUrl: './diabete.component.html',
  styleUrls: ['./diabete.component.css']
})
export class DiabeteComponent implements OnInit {

  @Input() newEvent: Observable<void> = new Observable<void>();

  result:string="";
  resultSubscription: Subscription = new Subscription();


  constructor(private diabeteService:DiabeteService,) { }

  ngOnInit(): void {
    this.getResult();
    this.result = this.diabeteService.result[0];
    console.log("result in ngOnInit : ", this.result)
  }

  getResult(){
    this.resultSubscription = this.diabeteService.resultSubject.subscribe(
      (result:string[])=>{
        this.result = result[0];
        console.log("result in getDiabete : ", this.result)
      }
    )
  }

}
