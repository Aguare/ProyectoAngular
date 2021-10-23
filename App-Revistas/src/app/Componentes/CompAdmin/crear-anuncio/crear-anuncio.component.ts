import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SafeResourceUrl } from '@angular/platform-browser';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Info } from 'src/app/Objetos/Info';
import { Base64Service } from 'src/app/Servicios/ObtenerObjetos/Base64.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-crear-anuncio',
  templateUrl: './crear-anuncio.component.html',
  styleUrls: ['./crear-anuncio.component.css']
})
export class CrearAnuncioComponent implements OnInit {

  //Variables auxiliares
  mensaje: Info = new Info(false, "Error", "No hay anunciantes registrados");
  espera: boolean = false;
  validarForm: FormGroup;

  //Variables logicas
  anunciantes: Anunciante[];
  path: string = "/assets/img/cheems.jpg";
  foto: File | null = null;

  //Variables para formulario
  anunciante: string;
  tipoAnuncio: number = 1;
  texto: string;
  video_url: string;
  costo: number;
  dias: number;
  imagen: string;

  constructor(
    private obtener: ObtenerObjetosService,
    private registrar: RegistrarService,
    private base: Base64Service
  ) {
    this.obtener.obtenerAnunciantes().subscribe((respuesta: Anunciante[]) => {
      this.anunciantes = respuesta;
      if (this.anunciantes.length == 0) {
        this.espera = true;
      }
    }, (error: any) => {
      this.mensaje = error.error;
      this.espera = true;
    }
    );
  }

  ngOnInit(): void {
    this.validarForm = new FormGroup({
      anunciante: new FormControl('', Validators.required),
      tipo: new FormControl(1, Validators.required),
      texto: new FormControl('', Validators.required),
      costo: new FormControl(1, Validators.required),
      dias: new FormControl(1, Validators.required)
    });
  }

  obtenerImagen(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.foto = files.item(0);
      this.base.extraerBase64(this.foto).then((foto: any) => {
        this.path = foto.base;
      });
    }
  }

  cambioTipoAnuncio() {
    this.tipoAnuncio = this.validarForm.get('tipo')?.value;
  }

  registrarAnuncio() {

  }
}
