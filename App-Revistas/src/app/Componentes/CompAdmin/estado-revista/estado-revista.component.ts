import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-estado-revista',
  templateUrl: './estado-revista.component.html',
  styleUrls: ['./estado-revista.component.css']
})
export class EstadoRevistaComponent implements OnInit {

  //Variables auxiliares
  espera: boolean = false;
  mensaje: Info = new Info(false, "Error", "No existe ninguna revista con ese ID");
  validarForm: FormGroup;
  cargado: boolean = false;

  //variables logica
  revista: Revista;
  idRevista: string;
  estado = false;

  constructor(
    private ruta: ActivatedRoute,
    private obtener: ObtenerObjetosService,
    private builder: FormBuilder,
    private registrar: RegistrarService
  ) {
    let temporal = this.ruta.snapshot.paramMap.get("idRevista");
    if (temporal != null) {
      this.idRevista = temporal;
    }
    this.validarForm = this.builder.group({
      costo: new FormControl ('1', Validators.required),
      estado: new FormControl ('true', Validators.required),
      fecha: new FormControl('2021-10-25',Validators.required)
    });
  }

  ngOnInit(): void {
    this.obtener.obtenerRevista(this.idRevista).subscribe((respuesta: Revista) => {
      this.revista = respuesta;
      this.cargado = true;
    },
      (error: any) => {
        this.mensaje = error.error;
        this.espera = true;
      }
    );
  }

  cambioEstado() {
    // let input = document.getElementById("costo");
    // this.estado = this.validarForm.get('estado')?.value();
    // alert(this.estado);
    // if (this.estado) {
    //   input?.removeAttribute("disabled");
    // } else {
    //   this.validarForm.get('costo')?.disable();
    // }
  }

  cambiarEstadoRevista() {
    if (this.validarForm.valid) {
      this.revista.fecha = this.validarForm.value.fecha;
      this.estado = this.validarForm.value.estado;
      if (this.estado == true) {
        this.revista.aprobado = true;
        this.revista.precio_costo = this.validarForm.value.costo;
      } else {
        this.revista.aprobado = false;
        this.revista.precio_costo = -1;
      }
      this.registrar.registrarEstadoRevista(this.revista).subscribe((respuesta: Info) => {
        this.mensaje = respuesta;
          this.espera = true;
          let input = document.getElementById("boton")?.setAttribute("disabled", "");
      },
        (error: any)=>{
          this.mensaje = error.error;
          this.espera = true;
        }      
      );
    }
  }
}
