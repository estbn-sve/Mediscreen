import { Component, OnInit } from '@angular/core';
import {Patient} from "../../../entity/patient";
import {Note} from "../../../entity/note";
import {Subscription} from "rxjs";
import {PatientService} from "../../../service/patient.service";
import {NoteService} from "../../../service/note.service";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {
  // @ts-ignore
  patient:Patient
  patients:Patient[]=[]
  notes:Note[] = []

  patientSubscription: Subscription = new Subscription();
  notesSubscription: Subscription = new Subscription();

  constructor(private patientService:PatientService,
              private noteService:NoteService) { }

  ngOnInit(): void {
    this.getPatient();
    this.getNotes();
    this.patient = this.patientService.patient[0];
    console.log("patient in ngOnInit : ", this.patient)
    this.notes = this.noteService.notes;
    console.log("notes in ngOnInit : ", this.notes)
  }

  getPatient(){
    this.patientSubscription = this.patientService.allPatientsSubject.subscribe(
      (patients:Patient[])=>{
        this.patient = patients[0];
        console.log("patient in getPatient : ", this.patient)
      })
  }

  getNotes(){
    this.notesSubscription = this.noteService.notesSubject.subscribe(
      (notes:Note[])=>{
        this.notes = notes;
        console.log("!!!!!!notes in getNote : ", this.notes)
      }
    )
  }

  addNote(){
  }

}
