import { TestBed } from '@angular/core/testing';

import { AutobaseService } from './autobase.service';

describe('AutobaseService', () => {
  let service: AutobaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutobaseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
