import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerAnunciantesComponent } from './ver-anunciantes.component';

describe('VerAnunciantesComponent', () => {
  let component: VerAnunciantesComponent;
  let fixture: ComponentFixture<VerAnunciantesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerAnunciantesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerAnunciantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
