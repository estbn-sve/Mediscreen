import { Component, OnInit } from '@angular/core';
import {Patient} from "../../entity/patient";
import {Note} from "../../entity/note";
import {PatientService} from "../../service/patient.service";
import {NoteService} from "../../service/note.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  constructor(private patientService:PatientService,
              private noteService:NoteService) { }

  ngOnInit(): void {
  }


}
