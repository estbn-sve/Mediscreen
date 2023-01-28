import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../service/patient.service";
import {Patient} from "../../entity/patient";
import {Subscription} from "rxjs";
import {NoteService} from "../../service/note.service";
import {Note} from "../../entity/note";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {DetailComponent} from "../detail/detail.component";
import {DiabeteService} from "../../service/diabete.service";


@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  patients : Patient[]= [];

  patientsSubscription: Subscription = new Subscription();

  constructor(private patientService: PatientService,
              private noteService: NoteService,
              private router: Router,
              private diabeteService : DiabeteService) { }

  ngOnInit(): void {
    this.getPatients();
  }

  private getPatients(){
    this.patientsSubscription = this.patientService.allPatientsSubject.subscribe(
      (patients:Patient[])=>{
        this.patients = patients;
      }
    )
    this.patientService.getAllPatients();
  }

  public rootToNotesByPatient(id:number, lastNamePatient:string){
    this.patientService.getPatient(id);
    this.noteService.getNoteByIdPatient(lastNamePatient);
    this.diabeteService.getNoteByIdPatient(String(id));
    this.router.navigate(['detail'])
  }

  public deletePatient(id:number){
    this.patientsSubscription = this.patientService.deleteSubject.subscribe(
      (patients:Patient[])=>{
        this.patients = patients;
      }
    )
    this.patientService.deletePatient(id);
  }

}
