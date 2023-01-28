import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Note} from "../entity/note";

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  public notes: Note[] = [];

  private baseUrl = environment.baseUrlNote;

  notesSubject = new Subject<Note[]>();

  constructor(private httpClient: HttpClient,) {
  }

  emitNotesSubject() {
    this.notesSubject.next(this.notes.slice());
  }

  getNoteByIdPatient(idPatient: string) {
    console.log("GET Note for : ",idPatient)
    this.httpClient.get<Note[]>(`${this.baseUrl}` + "note/patient/" + idPatient)
      .subscribe({
        next: response => {
          console.log("GET Note ok ", response);
          this.notes = response;
          this.emitNotesSubject();
        }, error: e => {
          console.error("GET error : " + e);
        }
      })
  }
}
