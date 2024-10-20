import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutobaseDetailsComponent } from './autobase-details/autobase-details.component';
import { AutobaseListComponent } from './autobase-list/autobase-list.component';
import { AutobaseCenterComponent } from './autobase-center/autobase-center.component';
import { AutobaseFormComponent } from './autobase-form/autobase-form.component';
import { AutobaseRoutingModule } from './autobase-routing.module';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AutobaseDetailsComponent,
    AutobaseListComponent,
    AutobaseCenterComponent,
    AutobaseFormComponent
  ],
  imports: [
    CommonModule,
    AutobaseRoutingModule,
    FormsModule
  ],
  exports: [
    AutobaseListComponent
  ]
})
export class AutobaseModule { }
