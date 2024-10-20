import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getFirestore, provideFirestore } from '@angular/fire/firestore';
import { AutobaseModule } from './autobase/autobase.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AutobaseModule,
    FormsModule
  ],
  providers: [
    provideFirebaseApp(() => initializeApp({"projectId":"bsu-web","appId":"1:475638672707:web:bac8daef2855808da4d3f6","storageBucket":"bsu-web.appspot.com","apiKey":"AIzaSyByLcUZUJU2z4t70ytALqHGzQ9wPE3WnB4","authDomain":"bsu-web.firebaseapp.com","messagingSenderId":"475638672707"})),
    provideFirestore(() => getFirestore())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
