import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TripDetailsComponent } from './trips/trip-details/trip-details.component';
import { TripCenterComponent } from './trips/trip-center/trip-center.component';

const routes: Routes = [
  {
    path: '',
    component: TripCenterComponent,
  },
  {
    path: 'trip/:id',
    component: TripDetailsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
