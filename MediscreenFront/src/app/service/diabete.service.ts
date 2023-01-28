import { Injectable } from '@angular/core';
import {PatientService} from "./patient.service";
import {NoteService} from "./note.service";
import {Patient} from "../entity/patient";
import {Note} from "../entity/note";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DiabeteService {
  result:string[]=[]
  private baseUrl = environment.baseUrlDiabete;

  resultSubject = new Subject<string[]>();

  constructor(private httpClient: HttpClient,) { }

  emitResultSubject() {
    this.resultSubject.next(this.result.slice());
  }

  getNoteByIdPatient(idPatient: string) {
    console.log("GET result diabete")
    this.httpClient.get<string[]>(`${this.baseUrl}` + "diabete/id/" + idPatient)
      .subscribe({
        next: response => {
          console.log("GET result ok ", response);
          this.result = response;
          this.emitResultSubject();
        }
        // , error: e => {
        //   console.error("GET error : " + e);
        // }
      })
  }
}
