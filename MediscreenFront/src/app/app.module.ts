import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PatientComponent } from './composant/patient/patient.component';
import {RouterModule} from "@angular/router";
import { MenuComponent } from './composant/menu/menu.component';
import { HomeComponent } from './composant/home/home.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    MenuComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'patient', component: PatientComponent},
      {path: 'home', component: HomeComponent}
    ], {useHash: false})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
