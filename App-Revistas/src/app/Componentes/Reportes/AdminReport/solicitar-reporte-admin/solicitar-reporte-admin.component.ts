import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Backend } from 'src/app/Objetos/Backend';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-solicitar-reporte-admin',
  templateUrl: './solicitar-reporte-admin.component.html',
  styleUrls: ['./solicitar-reporte-admin.component.css']
})
export class SolicitarReporteAdminComponent implements OnInit {
  //Variables auxiliares
  mensaje: Info = new Info(false, "Error", "Sin Conexión");
  espera: boolean = true;

  //Variables lógicas
  revistas: Revista[];
  idRevista: number = 0;
  fecha_inicio: string = "vacio";
  fecha_final: string = "vacio";
  opcionReporte: number = 1;

  //Previsualizar reporte
  path: string;
  url: SafeResourceUrl;

  constructor(
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService,
    private sanitizer: DomSanitizer
  ) {
    this.obtener.obtenerRevistasAceptadas().subscribe((respuesta: Revista[]) => {
      this.revistas = respuesta;
      this.espera = false;
    },
      (error: any) => {
        this.mensaje = error.error;
        this.espera = true;
      }
    );
  }

  ngOnInit(): void {
  }

  cambioReporte(selec: any) {
    this.opcionReporte = selec;
  }

  cambioRevista(selec: any) {
    this.idRevista = selec;
  }

  obtenerLink() {
    let url = Backend.Path + "AdminReportes?opcionReporte=" + this.opcionReporte;
    url += "&fecha_inicio=" + this.fecha_inicio;
    url += "&fecha_final=" + this.fecha_final;
    url += "&idRevista=" + this.idRevista;
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  generarReporte() {
    if (this.fecha_final == "" || this.fecha_inicio == "") {
      this.fecha_final = "vacio";
      this.fecha_inicio = "vacio";
    }
    if(this.opcionReporte == 4 || this.opcionReporte == 5){
      this.idRevista = 0;
    }
    this.obtenerLink();
  }

}
