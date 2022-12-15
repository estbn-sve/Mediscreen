import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PatientComponent } from './composant/patient/patient.component';
import {RouterModule} from "@angular/router";
import { MenuComponent } from './composant/menu/menu.component';
import { HomeComponent } from './composant/home/home.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { NoteComponent } from './composant/detail/note/note.component';
import { DetailComponent } from './composant/detail/detail.component';
import { DiabeteComponent } from './composant/detail/diabete/diabete.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    MenuComponent,
    HomeComponent,
    NoteComponent,
    DetailComponent,
    DiabeteComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'patient', component: PatientComponent},
      {path: 'home', component: HomeComponent},
      {path:'detail',component:DetailComponent}
    ], {useHash: false})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
