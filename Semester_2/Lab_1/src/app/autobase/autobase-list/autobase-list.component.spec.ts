import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutobaseListComponent } from './autobase-list.component';

describe('AutobaseListComponent', () => {
  let component: AutobaseListComponent;
  let fixture: ComponentFixture<AutobaseListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AutobaseListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutobaseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
