import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalheUsuario } from './detalhe-usuario';

describe('DetalheUsuario', () => {
  let component: DetalheUsuario;
  let fixture: ComponentFixture<DetalheUsuario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalheUsuario],
    }).compileComponents();

    fixture = TestBed.createComponent(DetalheUsuario);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
