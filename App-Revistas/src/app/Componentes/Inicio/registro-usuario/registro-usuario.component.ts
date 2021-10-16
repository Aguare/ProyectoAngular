import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, MinValidator, Validators } from '@angular/forms';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { Cliente } from 'src/app/Objetos/Cliente';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { Perfil } from 'src/app/Objetos/Perfil';
import { Usuario } from 'src/app/Objetos/Usuario';
import { RedireccionarService } from 'src/app/Servicios/Redireccionar.service';
import { RegistrarUsuarioService } from 'src/app/Servicios/Registros/RegistrarUsuario.service';

@Component({
  selector: 'app-registro-usuario',
  templateUrl: './registro-usuario.component.html',
  styleUrls: ['./registro-usuario.component.css']
})
export class RegistroUsuarioComponent implements OnInit {

  validarForm: FormGroup;
  passwordEquals: boolean = false;
  nombreUsuario: string = "";
  password: string = "";
  passwordConfirm: string = "";
  tipoUsuario: string = "";
  descripcion: string = "";
  etiquetasSeleccionadas: Etiqueta[] = [];
  mensajeFormulario: boolean = false;
  mensajeError: string = "Por favor llene todos los campos";

  constructor(
    private formBuilder: FormBuilder, 
    public enviarRegistro: RegistrarUsuarioService,
    public redireccionar: RedireccionarService
    ) {
    this.validarForm = new FormGroup({
      nombreUsuario: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      passwordConfirm: new FormControl('', [Validators.required]),
      tipoUsuario: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', Validators.min(0))
    });
  }

  ngOnInit(): void {
  }

  comprobarPasswords(event: any) {
    this.passwordConfirm = event.target.value;
    this.password = this.validarForm.value.password;
    this.tipoUsuario = this.validarForm.value.tipoUsuario;
    this.descripcion = this.validarForm.value.descripcion;
    if (this.password == this.passwordConfirm) {
      this.passwordEquals = true;
    } else {
      this.passwordEquals = false;
    }
  }

  actualizarEtiquetas(etiquetas: Etiqueta[]) {
    this.etiquetasSeleccionadas = etiquetas;
  }

  registrar() {
    if (this.validarForm.valid) {
      this.tipoUsuario = this.validarForm.value.tipoUsuario;
      this.nombreUsuario = this.validarForm.value.nombreUsuario;
      this.mensajeFormulario = false;
      let cantidad = this.etiquetasSeleccionadas.length;
      if (cantidad > 2) {
        let cliente = new Cliente(new Usuario(this.tipoUsuario, this.nombreUsuario, this.password), new Perfil(this.descripcion, this.etiquetasSeleccionadas));
        this.enviarRegistro.registrarUsuario(cliente).subscribe((respuesta: Info) =>{
          this.redireccionar.enviarPagina("Mensaje");
        },
        error => this.redireccionar.enviarPagina("ErrorConexion")
        );
      } else {
        this.mensajeError = "Debe seleccionar al menos 3 categorias o etiquetas";
        this.mensajeFormulario = true;
      }
    } else {
      this.mensajeFormulario = true;
      this.mensajeError = "Por favor llene todos los campos";
    }
  }
}
