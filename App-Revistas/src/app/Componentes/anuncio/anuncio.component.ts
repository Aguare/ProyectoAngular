import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Anuncio } from 'src/app/Objetos/Anuncio';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

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
    private sanitizer: DomSanitizer
  ) {
    this.usuario = alma.obtenerUsuario();
    this.obtener.obtenerAnunciosCliente(this.usuario.nombreUsuario).subscribe((respuesta: Anuncio[]) => {
      this.anuncios = respuesta;
      this.anuncioAleatorio();
    });
  }

  ngOnInit(): void {
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
}
