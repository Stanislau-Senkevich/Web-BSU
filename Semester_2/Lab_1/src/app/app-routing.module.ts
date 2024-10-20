import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutobaseFormComponent } from './autobase/autobase-form/autobase-form.component';

const routes: Routes = [
  { path: 'autobase', loadChildren: () => import('./autobase/autobase.module').then(m => m.AutobaseModule) },
  { path: 'form', component: AutobaseFormComponent }, 
  { path: 'form/:id', component: AutobaseFormComponent }, 
  { path: '', redirectTo: '/autobase', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
