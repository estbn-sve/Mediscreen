import { Injectable } from '@angular/core';
import {Patient} from "../entity/patient";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {


  // @ts-ignore
  public patient : Patient[]=[];
  private patients : Patient []=[];
  private baseUrl = environment.baseUrlPatient;

  allPatientsSubject = new Subject<Patient[]>()
  patientSubject = new Subject<Patient[]>()
  deleteSubject = new Subject<Patient[]>();

  constructor(private httpClient: HttpClient,) {}

  emitAllPatientsSubject(){
    this.allPatientsSubject.next(this.patients.slice());
  }
  emitPatientSubject(){
    this.patientSubject.next(this.patient.slice());
  }

  emitDeletePatientSubject(){
    this.deleteSubject.next(this.patient.slice());
  }

  getAllPatients(){
    console.log("GET all Patients")
    this.httpClient.get<Patient[]>(`${this.baseUrl}`+"patient")
      .subscribe({
        next : response => {
          console.log("GET Patients ok ", response);
          this.patients =response;
          this.emitAllPatientsSubject();
        },error:e => {
          console.error("GET error : "+e);
        }
      })
  }

  getPatient(id:number){
    console.log("GET Patient with id :",id)
    this.httpClient.get<Patient[]>(`${this.baseUrl}`+"patient/"+id)
      .subscribe({
        next : response => {
          console.log("GET Patient ok ", response);
          this.patient =response;
          this.emitAllPatientsSubject();
        },error:e => {
          console.error("GET error : "+e);
        }
      })
  }

  deletePatient(id:number){
    console.log("DELETE Patient with id :",id)
    this.httpClient.delete<Patient[]>(`${this.baseUrl}`+"patient/"+id)
      .subscribe({
        next : response => {
          console.log("DELETE Patient ok ", response);
          this.patient =response;
          this.emitDeletePatientSubject();
        },error:e => {
          console.error("DELETE error : "+e);
        }
      })
  }
}
