import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerRevistasComponent } from './ver-revistas.component';

describe('VerRevistasComponent', () => {
  let component: VerRevistasComponent;
  let fixture: ComponentFixture<VerRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerRevistasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
