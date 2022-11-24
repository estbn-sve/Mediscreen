import { Injectable } from '@angular/core';
import {Patient} from "../entity/patient";
import {environment} from "../../environments/environment";
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Note} from "../entity/note";

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  // @ts-ignore
  private note : Note;

  private baseUrl = environment.baseUrlNote;

  noteSubject = new Subject<Note[]>()

  constructor(private httpClient: HttpClient,) {}

  emitPatientSubject(){
    // @ts-ignore
    this.noteSubject.next(this.note.slice());
  }

  getNoteByIdPatient(id:number){
    console.log("GET Note")
    this.httpClient.get<string[]>(`${this.baseUrl}`+"note/"+id)
      .subscribe({
        next : response => {
          console.log("GET ok ", response);
          // @ts-ignore
          this.note.note =response;
          this.emitPatientSubject();
        },error:e => {
          console.error("GET error : "+e);
        }
      })
  }
}
