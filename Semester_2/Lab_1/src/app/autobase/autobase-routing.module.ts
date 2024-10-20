import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AutobaseCenterComponent } from './autobase-center/autobase-center.component';
import { AutobaseListComponent } from './autobase-list/autobase-list.component';
import { AutobaseDetailsComponent } from './autobase-details/autobase-details.component';

const routes: Routes = [
  { path: '',
    component: AutobaseCenterComponent,
    children: [
      {
        path: '',
        component: AutobaseListComponent,
        children: [
          {
            path: ':id',
            component: AutobaseDetailsComponent
          }
        ]
      },
    ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class AutobaseRoutingModule { }
