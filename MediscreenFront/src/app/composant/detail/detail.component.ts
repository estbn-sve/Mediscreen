import {Component, OnInit} from '@angular/core';

import {PatientService} from "../../service/patient.service";
import {NoteService} from "../../service/note.service";
import {DiabeteService} from "../../service/diabete.service";
import {Note} from "../../entity/note";
import {Patient} from "../../entity/patient";
import {Subject} from "rxjs";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  patient: Patient[]=[]
  result: String = "";
  notes: Note[] = []

  newEvent: Subject<void> = new Subject<void>();

  constructor(private patientService: PatientService,
              private noteService: NoteService,
              private diabeteService: DiabeteService) {
  }

  ngOnInit(): void {
    this.patient= this.patientService.patient
    this.notes=this.noteService.notes
    this.result=this.diabeteService.result[0]
  }

  new() {
    console.log("new Add transaction componennt")
    this.newEvent.next();
  }


}
