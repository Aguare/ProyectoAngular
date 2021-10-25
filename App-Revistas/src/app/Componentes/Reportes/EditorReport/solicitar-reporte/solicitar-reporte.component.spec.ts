import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitarReporteComponent } from './solicitar-reporte.component';

describe('SolicitarReporteComponent', () => {
  let component: SolicitarReporteComponent;
  let fixture: ComponentFixture<SolicitarReporteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitarReporteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitarReporteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
