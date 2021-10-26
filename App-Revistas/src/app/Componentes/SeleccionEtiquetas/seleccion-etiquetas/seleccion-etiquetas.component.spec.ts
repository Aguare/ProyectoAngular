import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeleccionEtiquetasComponent } from './seleccion-etiquetas.component';

describe('SeleccionEtiquetasComponent', () => {
  let component: SeleccionEtiquetasComponent;
  let fixture: ComponentFixture<SeleccionEtiquetasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeleccionEtiquetasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeleccionEtiquetasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
