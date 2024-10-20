import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutobaseFormComponent } from './autobase-form.component';

describe('AutobaseFormComponent', () => {
  let component: AutobaseFormComponent;
  let fixture: ComponentFixture<AutobaseFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AutobaseFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutobaseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
