import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Usuario } from '../Objetos/Usuario';
import { AlmacenamientoLocalService } from '../Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { RedireccionarService } from '../Servicios/Redireccionar.service';
import { LoginService } from '../Servicios/Sesion/Login.service';

@Component({
  selector: 'app-iniciosesion',
  templateUrl: './iniciosesion.component.html',
  styleUrls: ['./iniciosesion.component.css']
})
export class IniciosesionComponent implements OnInit {

  validarForm: FormGroup;
  usuario: Usuario;

  constructor(private formBuilder: FormBuilder, public iniciar: LoginService, public almacenLS: AlmacenamientoLocalService, public redireccionar: RedireccionarService) {
    this.usuario = new Usuario("", "");
    this.validarForm = new FormGroup({
      nombreUsuario: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    this.validarForm = new FormGroup({
      nombreUsuario: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  iniciarSesion() {
    if (this.validarForm.valid) {
      this.usuario.nombreUsuario = this.validarForm.value.nombreUsuario;
      this.usuario.password = this.validarForm.value.password;
      this.iniciar.validarUsuario(this.usuario.nombreUsuario, this.usuario.password).subscribe((exito: Usuario) => {
        if (exito.nombreUsuario != "helado") {
          alert("mamalon1");
          this.almacenLS.agregar(exito, "Usuario");
          //Castear JSON a objeto 
          this.usuario = JSON.parse(`${this.almacenLS.obtener("Usuario")}`);
          alert(this.usuario.nombreUsuario);
          alert(this.usuario.password);
          this.redireccionar.enviarPagina("InicioLector");
        } else {
          alert("no chona");
        }
      });
    }
  }

}
