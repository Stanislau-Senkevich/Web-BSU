import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutobaseDetailsComponent } from './autobase-details.component';

describe('AutobaseDetailsComponent', () => {
  let component: AutobaseDetailsComponent;
  let fixture: ComponentFixture<AutobaseDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AutobaseDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutobaseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
