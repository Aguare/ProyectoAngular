import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Anuncio } from 'src/app/Objetos/Anuncio';
import { Info } from 'src/app/Objetos/Info';
import { Usuario } from 'src/app/Objetos/Usuario';
import { Visualizacion } from 'src/app/Objetos/Visualizacion';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-anuncio',
  templateUrl: './anuncio.component.html',
  styleUrls: ['./anuncio.component.css']
})
export class AnuncioComponent implements OnInit {

  usuario: Usuario;
  anuncios: Anuncio[];
  anuncio: Anuncio;
  urlVideo: SafeResourceUrl;

  constructor(
    private obtener: ObtenerObjetosService,
    private alma: AlmacenamientoLocalService,
    private ruta: Router,
    private sanitizer: DomSanitizer,
    private registrar: RegistrarService
  ) {
    this.usuario = alma.obtenerUsuario();
    this.obtener.obtenerAnunciosCliente(this.usuario.nombreUsuario).subscribe((respuesta: Anuncio[]) => {
      if (respuesta != null) {
        this.anuncios = respuesta;
        this.anuncioAleatorio();
        this.registrarVisualizacion();
      }

    });
  }

  ngOnInit(): void {
    this.registrarVisualizacion();
  }

  anuncioAleatorio() {
    let numero = this.obtenerRandom(0, this.anuncios.length);
    this.anuncio = this.anuncios[numero];
    if (this.anuncio.tipo_anuncio == 3) {
      this.urlVideo = this.obtenerLinkVideo(this.anuncio.video_url);
    }
  }

  //Excluye al máximo
  obtenerRandom(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min)) + min;
  }

  obtenerLinkVideo(url: string): SafeResourceUrl {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  registrarVisualizacion() {
    if (this.anuncio != null) {
      let fecha = prompt("INGRESE LA FECHA DE VISUALIZACIÓN DEL ANUNCIO FORMATO YYYY-MM-DD");
      if (fecha != null) {
        let visualizacion = new Visualizacion(1, this.ruta.url, fecha, this.anuncio.idAnuncio, this.anuncio.A_nombre_anunciante);
        this.registrar.registrarVisualizacion(visualizacion).subscribe((respuesta: Info) => {
        });
      }
    }
  }
}
