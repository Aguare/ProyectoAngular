import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtiquetaComponent } from './etiqueta.component';

describe('EtiquetaComponent', () => {
  let component: EtiquetaComponent;
  let fixture: ComponentFixture<EtiquetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EtiquetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EtiquetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
