import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerRevistaComponent } from './ver-revista.component';

describe('VerRevistaComponent', () => {
  let component: VerRevistaComponent;
  let fixture: ComponentFixture<VerRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerRevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
