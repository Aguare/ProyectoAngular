import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SinConexionComponent } from './sin-conexion.component';

describe('SinConexionComponent', () => {
  let component: SinConexionComponent;
  let fixture: ComponentFixture<SinConexionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SinConexionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SinConexionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
