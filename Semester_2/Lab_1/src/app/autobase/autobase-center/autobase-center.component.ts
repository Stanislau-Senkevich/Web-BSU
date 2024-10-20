import { Component } from '@angular/core';
import { Autobase } from '../model/Autobase';
import { AutobaseService } from '../services/autobase.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-autobase-center',
  templateUrl: './autobase-center.component.html',
  styleUrl: './autobase-center.component.css'
})
export class AutobaseCenterComponent {
  autobase: Autobase[] = [];

  constructor(private service: AutobaseService, private router: Router){}

  async ngOnInit(): Promise<void> {
    this.autobase = await this.service.getAutobase();
  }

  onAdd(): void {
    this.router.navigate(['/form']);
  }
}
