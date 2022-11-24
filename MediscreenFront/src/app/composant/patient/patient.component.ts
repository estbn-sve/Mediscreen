import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../service/patient.service";
import {Patient} from "../../entity/patient";
import {Subscription} from "rxjs";
import {NoteService} from "../../service/note.service";
import {Note} from "../../entity/note";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  closeResult ="";

  patients : Patient[]= [];
  // @ts-ignore
  note:Note;

  patientsSubscription: Subscription = new Subscription();
  noteSubscription: Subscription = new Subscription();

  constructor(private patientService: PatientService,
              private noteService: NoteService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getPatients();
  }

  private getPatients(){
    this.patientsSubscription = this.patientService.patientSubject.subscribe(
      (patients:Patient[])=>{
        this.patients = patients;
      }
    )
    this.patientService.getAllPatients();
  }

  public getNoteByPatientId(id:number){
    this.noteSubscription = this.noteService.noteSubject.subscribe(
      // @ts-ignore
      (note:Note)=>{
        this.note = note;
      }
    )
    this.noteService.getNoteByIdPatient(id);
  }
  open(content:any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
      (result) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      },
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
