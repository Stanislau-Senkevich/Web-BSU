import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutobaseCenterComponent } from './autobase-center.component';

describe('AutobaseCenterComponent', () => {
  let component: AutobaseCenterComponent;
  let fixture: ComponentFixture<AutobaseCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AutobaseCenterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutobaseCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
