import { Component } from '@angular/core';
import { Autobase } from '../model/Autobase';
import { ActivatedRoute } from '@angular/router';
import { AutobaseService } from '../services/autobase.service';

@Component({
  selector: 'app-autobase-details',
  templateUrl: './autobase-details.component.html',
  styleUrl: './autobase-details.component.css'
})
export class AutobaseDetailsComponent {
  autobase: Autobase | null = null;

  constructor(
    private route: ActivatedRoute,
    private autobaseService: AutobaseService
  ) {}

  async ngOnInit(): Promise<void> {
    this.route.paramMap.subscribe(async data => {
      let id = data.get('id');
      id ? this.autobase = await this.autobaseService.getById(+id) : null;
    });
  }
}
