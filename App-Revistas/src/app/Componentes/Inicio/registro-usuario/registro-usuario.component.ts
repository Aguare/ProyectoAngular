import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, MinValidator, Validators } from '@angular/forms';
import { Cliente } from 'src/app/Objetos/Cliente';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { Perfil } from 'src/app/Objetos/Perfil';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { Base64Service } from 'src/app/Servicios/ObtenerObjetos/Base64.service';
import { RedireccionarService } from 'src/app/Servicios/Redireccionar.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';
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
  foto: File | null = null;
  mostrarFoto: string;
  etiquetasSeleccionadas: Etiqueta[] = [];
  mensajeFormulario: boolean = false;
  mensajeError: Info = new Info(false, "Error", "Rellene todos los campos");

  constructor(
    private formBuilder: FormBuilder,
    public enviarRegistro: RegistrarUsuarioService,
    public redireccionar: RedireccionarService,
    public base: Base64Service,
    private subirArchivo: RegistrarService,
    private almacenamiento: AlmacenamientoLocalService
  ) {
    this.validarForm = this.formBuilder.group({
      nombreUsuario: ['', Validators.required],
      password: ['', Validators.required],
      passwordConfirm: ['', Validators.required],
      tipoUsuario: [2, Validators.required],
      descripcion: ['', Validators.required],
      archivo: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  obtenerImagen(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.foto = files.item(0);
      this.base.extraerBase64(this.foto).then((foto: any) => {
        this.mostrarFoto = foto.base;
      });
    }
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
      this.descripcion = this.validarForm.value.descripcion;
      this.mensajeFormulario = false;
      let cantidad = this.etiquetasSeleccionadas.length;
      if (cantidad > 2 && this.foto != null) {
        let cliente = new Cliente(new Usuario(this.tipoUsuario, this.nombreUsuario, this.password),
          new Perfil("", this.descripcion, this.etiquetasSeleccionadas));
        this.enviarRegistro.registrarUsuario(cliente, this.foto).subscribe((respuesta: Info) => {
          this.mensajeError = respuesta;
          this.mensajeFormulario = true;
          if (this.mensajeError.operacion) {
            this.redireccionar.enviarPagina("Mensaje");
          }
        },
          (error: any) => {
            this.mensajeError = error;
            this.mensajeFormulario = true;
          }
        );
      } else {
        this.mensajeError = new Info(false, "Error", "Debe seleccionar al menos 3 categorias o etiquetas");
        this.mensajeFormulario = true;
      }
    } else {
      this.mensajeFormulario = true;
      this.mensajeError = new Info(false, "Error", "Por favor rellene todos los campos");
    }
  }
}
