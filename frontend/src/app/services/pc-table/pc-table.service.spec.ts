import { TestBed } from '@angular/core/testing';

import { PcTableService } from './pc-table.service';

describe('PcTableService', () => {
  let service: PcTableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PcTableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
