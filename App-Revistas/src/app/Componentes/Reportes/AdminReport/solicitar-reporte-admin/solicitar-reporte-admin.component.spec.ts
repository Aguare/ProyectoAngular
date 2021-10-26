import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitarReporteAdminComponent } from './solicitar-reporte-admin.component';

describe('SolicitarReporteAdminComponent', () => {
  let component: SolicitarReporteAdminComponent;
  let fixture: ComponentFixture<SolicitarReporteAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitarReporteAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitarReporteAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
