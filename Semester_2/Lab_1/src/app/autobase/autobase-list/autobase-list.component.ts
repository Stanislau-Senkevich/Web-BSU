import { Component } from '@angular/core';
import { Autobase } from '../model/Autobase';
import { AutobaseService } from '../services/autobase.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-autobase-list',
  templateUrl: './autobase-list.component.html',
  styleUrl: './autobase-list.component.css'
})
export class AutobaseListComponent {
  autobase: Autobase[] = [];

  constructor(
    private service: AutobaseService,
    private router: Router,
  ) {}

  async ngOnInit(): Promise<void> {
    this.autobase = await this.service.getAutobase();
  }

  onSelect(autobase: Autobase): void {
    this.router.navigate(['autobase', autobase.id]);
  }

  async onDelete(id: number): Promise<void> {
    await this.service.deleteById(id);
    this.autobase = await this.service.getAutobase();
  }

  onEdit(id: number): void {
    this.router.navigate(['/form', id]);
  }
}
