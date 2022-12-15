import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../service/patient.service";
import {Patient} from "../../entity/patient";
import {Subscription} from "rxjs";
import {NoteService} from "../../service/note.service";
import {Note} from "../../entity/note";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {DetailComponent} from "../detail/detail.component";
import {DetailService} from "../../service/detail.service";


@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  closeResult ="";

  patients : Patient[]= [];
  note:Note[]=[];
  notes:Note[]=[];
  noteInHtml:string="pas de note";

  patientsSubscription: Subscription = new Subscription();
  noteSubscription: Subscription = new Subscription();
  notesSubscription: Subscription = new Subscription();

  constructor(private patientService: PatientService,
              private noteService: NoteService,
              private modalService: NgbModal,
              private router: Router,
              private detailService : DetailService) { }

  ngOnInit(): void {
    this.getPatients();
    // this.getNotes();
  }

  private getPatients(){
    this.patientsSubscription = this.patientService.allPatientsSubject.subscribe(
      (patients:Patient[])=>{
        this.patients = patients;
      }
    )
    this.patientService.getAllPatients();
  }
  // private getNotes(){
  //   this.notesSubscription = this.noteService.notesSubject.subscribe(
  //     (notes:Note[])=>{
  //       this.notes = notes;
  //     }
  //   )
  //   // this.noteService.getAllNote();
  // }

  public getNoteByPatientIdBDD(id:string){
    this.noteSubscription = this.noteService.notesSubject.subscribe(
      (notes:Note[])=>{
        this.note = notes;
      }
    )
    this.noteService.getNoteByIdPatient(id);
  }

  public getNoteByPatientId(id:string){
    let notes = this.notes;
    console.log("let notes : ",notes);
    const found = notes.filter(a => a.idPatient == id);
    console.log('found : ', found);
    this.noteInHtml = found[0].note;
  }

  public rootToNotesByPatient(id:number, lastNamePatient:string){
    this.router.navigate(['detail'])
    this.patientService.getPatient(id);
    this.noteService.getNoteByIdPatient(lastNamePatient);
  }

  open(content:any,id:string){
    this.getNoteByPatientId(id);
    console.log("Log NOTE : ",this.note)
    this.modal(content);
  }

  modal(content:any) {
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
