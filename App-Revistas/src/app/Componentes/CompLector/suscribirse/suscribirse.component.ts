import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Info } from 'src/app/Objetos/Info';
import { Pago } from 'src/app/Objetos/Pago';
import { Revista } from 'src/app/Objetos/Revista';
import { Suscripcion } from 'src/app/Objetos/Suscripcion';
import { Usuario } from 'src/app/Objetos/Usuario';
import { ValorSistema } from 'src/app/Objetos/ValorSistema';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-suscribirse',
  templateUrl: './suscribirse.component.html',
  styleUrls: ['./suscribirse.component.css']
})
export class SuscribirseComponent implements OnInit {

  idRevista: string;
  revista: Revista;
  mensaje: Info = new Info(true, "Ya estas suscrito", "Puedes ir a tus suscripciones");
  espera: boolean = true;
  valorSistema: ValorSistema;
  validarForm: FormGroup;

  //variables de retorno
  usuario: Usuario;
  total: number = 0;
  fechaInicio: string;
  fechaFinal: string = "";
  meses: number;
  esconder: boolean = false;

  constructor(
    private ruta: ActivatedRoute,
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService,
    private builder: FormBuilder,
    private registrar: RegistrarService
  ) {
    let idRevista = this.ruta.snapshot.paramMap.get("idRevista");
    if (idRevista != null) {
      this.usuario = this.almacenamiento.obtenerUsuario();
      this.idRevista = idRevista;
      this.validarForm = this.builder.group({
        fecha: ['2021-10-25', Validators.required],
        tipoSus: ['Mensual', Validators.required],
        meses: [1, Validators.required]
      });
    }
  }

  ngOnInit(): void {
    this.obtener.obtenerRevista(this.idRevista).subscribe((revista: Revista) => {
      this.revista = revista;
      this.espera = false;
      this.obtener.obtenerValorSistema().subscribe((respuesta: ValorSistema) => {
        this.valorSistema = respuesta;
      },
        (error: any) => {
          this.mensaje = error;
          this.espera = true;
        });
      this.calcularTotal();
    });
  }

  calcularTotal() {
    this.total = this.validarForm.value.meses * this.revista.precio_suscripcion;
  }

  cambiaTipo() {
    let input = document.getElementById("inputCM");
    let tipo = this.validarForm.value.tipoSus;
    if (tipo == "Anual") {
      input?.setAttribute("disabled", "");
      this.validarForm.get("meses")?.setValue(12);
      this.calcularTotal();
    } else {
      input?.removeAttribute("disabled");
    }
  }

  registrarSuscripcion() {
    this.fechaInicio = this.validarForm.value.fecha;
    this.meses = this.validarForm.value.meses;
    if (this.validarForm.valid) {
      let parteObtenido = this.valorSistema.porcentaje_comision * this.total;
      let parteEditor = this.total - parteObtenido;
      let pago = new Pago(1, parteObtenido, parteEditor, this.total);
      let suscripcion = new Suscripcion(1, this.fechaInicio, this.fechaFinal,
        this.meses, false, this.usuario, this.revista.idRevista,
        this.revista.usuarioCreador, pago);
      this.registrar.registrarSuscripcion(suscripcion).subscribe((respuesta: Info) => {
        this.mensaje = respuesta;
        this.espera = true;
        this.esconder = true;
      },
        (error: Info) => {
          this.mensaje = error;
          this.espera = true;
        }
      );
    } else {
      this.espera = true;
    }
  }

}
