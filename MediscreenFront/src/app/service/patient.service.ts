import { Injectable } from '@angular/core';
import {Patient} from "../entity/patient";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  public patient : Patient []=[];
  private patients : Patient []=[];
  private baseUrl = environment.baseUrlPatient;

  allPatientsSubject = new Subject<Patient[]>()
  // patientSubject = new Subject<Patient[]>()

  constructor(private httpClient: HttpClient,) {}

  emitAllPatientsSubject(){
    this.allPatientsSubject.next(this.patients.slice());
  }
  // emitPatientSubject(){
  //   this.patientSubject.next(this.patient.slice());
  // }

  getAllPatients(){
    console.log("GET Patient")
    this.httpClient.get<Patient[]>(`${this.baseUrl}`+"patient")
      .subscribe({
        next : response => {
          console.log("GET Patient ok ", response);
          this.patients =response;
          this.emitAllPatientsSubject();
        },error:e => {
          console.error("GET error : "+e);
        }
      })
  }

  getPatient(id:number){
    this.httpClient.get<Patient[]>(`${this.baseUrl}`+"patient/"+id)
      .subscribe({
        next : response => {
          console.log("GET Patient ok ", response);
          this.patient =response;
          this.emitAllPatientsSubject();
          console.log("patient get : ",this.patient);
        },error:e => {
          console.error("GET error : "+e);
        }
      })
  }
}
