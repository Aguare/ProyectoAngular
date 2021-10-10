/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { RegistrarService } from './Registrar.service';

describe('Service: Registrar', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RegistrarService]
    });
  });

  it('should ...', inject([RegistrarService], (service: RegistrarService) => {
    expect(service).toBeTruthy();
  }));
});
