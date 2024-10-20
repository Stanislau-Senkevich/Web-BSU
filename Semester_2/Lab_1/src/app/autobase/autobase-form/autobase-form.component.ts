import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Autobase } from '../model/Autobase';
import { AutobaseService } from '../services/autobase.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-autobase-form',
  templateUrl: './autobase-form.component.html',
  styleUrl: './autobase-form.component.css'
})
export class AutobaseFormComponent {
  @ViewChild('Form') form!: NgForm;
  model: Autobase = { id: 0, description: '', driver: ''};
  isEditing: boolean = false;

  constructor(
    private service: AutobaseService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  async ngOnInit() {
    const editedAutobaseId = this.route.snapshot.paramMap.get('id');
    if(editedAutobaseId) {
      const autobase = await this.service.getById(parseInt(editedAutobaseId));
      this.isEditing = true;
      if(autobase) {
        this.model = autobase;
      }
    }
    else {
      this.isEditing = false;
    }
  }

  async onSubmit(form: NgForm) {
    if(this.isEditing) {
      await this.service.update({id: this.model.id, ...form.value});
    }
    else {
      await this.service.add({id: await this.service.getMaxId(), ...form.value});
    }
    this.router.navigate(['']);
  }
}
