import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitarFechaComponent } from './solicitar-fecha.component';

describe('SolicitarFechaComponent', () => {
  let component: SolicitarFechaComponent;
  let fixture: ComponentFixture<SolicitarFechaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitarFechaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitarFechaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
