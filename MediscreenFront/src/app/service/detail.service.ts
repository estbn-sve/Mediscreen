import { Injectable } from '@angular/core';
import {PatientService} from "./patient.service";
import {NoteService} from "./note.service";
import {Patient} from "../entity/patient";
import {Note} from "../entity/note";

@Injectable({
  providedIn: 'root'
})
export class DetailService {
  // @ts-ignore
  patient:Patient
  notes:Note[] = []

  constructor(private patientService: PatientService,
              private noteService : NoteService) { }

}
