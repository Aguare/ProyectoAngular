import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Info } from 'src/app/Objetos/Info';
import { ValorSistema } from 'src/app/Objetos/ValorSistema';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-comision',
  templateUrl: './comision.component.html',
  styleUrls: ['./comision.component.css']
})
export class ComisionComponent implements OnInit {

  //Variables auxiliares
  mensaje: Info = new Info(false, "Error", "No hay valores en el Sistema");
  espera: boolean = false;

  //Variables logicas
  valoresSistema: ValorSistema[];
  valorActual: ValorSistema;
  porcentaje: number;
  fecha: string;

  constructor(
    private obtener: ObtenerObjetosService,
    private registrar: RegistrarService
  ) {
    this.obtener.obtenerValoresSistema().subscribe((respuesta: ValorSistema[]) => {
      this.valoresSistema = respuesta;
      this.valorActual = respuesta[respuesta.length - 1];
      this.porcentaje = this.valorActual.porcentaje_comision * 100;
      this.fecha = this.valorActual.fecha;
    },
      (error: any) => {
        this.mensaje = error.error;
        this.espera = true;
      }
    );
  }

  ngOnInit(): void {

  }

  cambiarValorSistema() {
    if (this.porcentaje > 0 && this.porcentaje < 100) {
      this.valorActual.porcentaje_comision = this.porcentaje / 100;
      this.valorActual.fecha = this.fecha;
      this.registrar.registrarComision(this.valorActual).subscribe((respuesta: Info) => {
        this.mensaje = respuesta;
        this.espera = true;
        this.valoresSistema.push(this.valorActual);
        let input = document.getElementById("boton")?.setAttribute("disabled", "");
      },
        (error: any) => {
          this.mensaje = error.error;
          this.espera = true;
        }
      );
    }
  }
}
