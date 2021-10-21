import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Backend } from 'src/app/Objetos/Backend';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-visualizar',
  templateUrl: './visualizar.component.html',
  styleUrls: ['./visualizar.component.css']
})
export class VisualizarComponent implements OnInit {

  nombreUsuario: string;
  idRevista: string;
  espera: boolean = true;
  revista: Revista;
  mensaje: Info = new Info(false, "Error", "Parámetros incorrectos");
  path: string;
  urlDescarga: SafeResourceUrl;

  constructor(
    private ruta: ActivatedRoute,
    private obtener: ObtenerObjetosService,
    private sanitizer: DomSanitizer,
    private almacenamiento: AlmacenamientoLocalService
  ) {
    let idRevista = this.ruta.snapshot.paramMap.get("idRevista");
    if (idRevista != null) {
      this.nombreUsuario = this.almacenamiento.obtenerUsuario().nombreUsuario;
      this.idRevista = idRevista;
    }
  }

  ngOnInit(): void {
    this.obtener.obtenerRevista(this.idRevista).subscribe((revista: Revista) => {
      this.revista = revista;
      this.espera = false;
      this.path = revista.revista;
      this.obtenerLinkDescarga();
    });
  }

  obtenerLinkDescarga(){
    let temp = Backend.Path + "SubirArchivo?opcion=2&path=" + this.path;
    this.urlDescarga = this.sanitizer.bypassSecurityTrustResourceUrl(temp);
  }

}
