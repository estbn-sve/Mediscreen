import { Injectable } from '@angular/core';
import {Patient} from "../entity/patient";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private patients : Patient []=[];
  private baseUrl = environment.baseUrl;

  patientSubject = new Subject<Patient[]>()

  constructor(private httpClient: HttpClient,) {}

  emitPatientSubject(){
    this.patientSubject.next(this.patients.slice());
  }

  getAllPatients(){
    console.log("GET Patient")
    this.httpClient.get<Patient[]>(`${this.baseUrl}`+"patient")
      .subscribe({
        next : response => {
          console.log("GET ok ", response);
          this.patients =response;
          this.emitPatientSubject();
        },error:e => {
          console.error("GET error : "+e);
        }
      })
  }
}
