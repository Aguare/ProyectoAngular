import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Info } from 'src/app/Objetos/Info';
import { RedireccionarService } from 'src/app/Servicios/Redireccionar.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-registrar-anunciante',
  templateUrl: './registrar-anunciante.component.html',
  styleUrls: ['./registrar-anunciante.component.css']
})
export class RegistrarAnuncianteComponent implements OnInit {

  mostrarMensaje: boolean = false;
  mensaje: Info = new Info(false, "Campos Vacíos", "Llene todos los campos");
  validarForm: FormGroup;
  nombreAnunciante: string = "";
  telefono: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    public redireccionar: RedireccionarService,
    public registrar: RegistrarService
  ) {
    this.validarForm = new FormGroup({
      nombreAnunciante: new FormControl('', [Validators.required, Validators.min(2)]),
      telefono: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
  }

  registrarAnunciante() {
    if (this.validarForm.valid) {
      this.telefono = this.validarForm.value.telefono;
      this.nombreAnunciante = this.validarForm.value.nombreAnunciante;
      this.registrar.registrarAnunciante(new Anunciante(this.nombreAnunciante, this.telefono)).subscribe((respuesta: Info) => {
        this.mensaje = respuesta;
        this.mostrarMensaje = true;
        this.validarForm.reset();
      },
        (error: any) => {
          this.mensaje = error.error;
          this.mostrarMensaje = true;
        }
      );
    } else {
      this.mostrarMensaje = true;
    }
  }
}
