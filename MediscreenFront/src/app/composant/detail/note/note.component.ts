import {Component, Input, OnInit} from '@angular/core';
import {Patient} from "../../../entity/patient";
import {Note} from "../../../entity/note";
import {Observable, Subscription} from "rxjs";
import {PatientService} from "../../../service/patient.service";
import {NoteService} from "../../../service/note.service";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  @Input() newEvent: Observable<void> = new Observable<void>();

  notes:Note[] = []

  notesSubscription: Subscription = new Subscription();

  constructor(private noteService:NoteService,
              ) { }

  ngOnInit(): void {
    this.getNotes();
    this.notes = this.noteService.notes;
    console.log("notes in ngOnInit : ", this.notes)
  }

  getNotes(){
    this.notesSubscription = this.noteService.notesSubject.subscribe(
      (notes:Note[])=>{
        this.notes = notes;
        console.log("notes in getNote : ", this.notes)
      }
    )
  }


}
