import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarjetaRevistaComponent } from './tarjeta-revista.component';

describe('TarjetaRevistaComponent', () => {
  let component: TarjetaRevistaComponent;
  let fixture: ComponentFixture<TarjetaRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TarjetaRevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TarjetaRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
