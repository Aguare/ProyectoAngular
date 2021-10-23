import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaRevistasComponent } from './lista-revistas.component';

describe('ListaRevistasComponent', () => {
  let component: ListaRevistasComponent;
  let fixture: ComponentFixture<ListaRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaRevistasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
