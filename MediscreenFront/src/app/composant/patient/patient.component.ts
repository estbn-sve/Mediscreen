import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../service/patient.service";
import {Patient} from "../../entity/patient";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  patients : Patient[]= [];

  patientsSubscription: Subscription = new Subscription();

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.getPatients();
  }

  private getPatients(){
    this.patientsSubscription = this.patientService.patientSubject.subscribe(
      (patients:Patient[])=>{
        this.patients = patients;
      }
    )
    this.patientService.getAllPatients();
  }

}
