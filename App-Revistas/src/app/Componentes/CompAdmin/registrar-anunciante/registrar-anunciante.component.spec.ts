import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarAnuncianteComponent } from './registrar-anunciante.component';

describe('RegistrarAnuncianteComponent', () => {
  let component: RegistrarAnuncianteComponent;
  let fixture: ComponentFixture<RegistrarAnuncianteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarAnuncianteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarAnuncianteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
