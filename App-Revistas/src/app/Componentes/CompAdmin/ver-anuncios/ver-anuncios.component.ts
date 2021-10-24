import { Component, OnInit } from '@angular/core';
import { Anuncio } from 'src/app/Objetos/Anuncio';
import { Info } from 'src/app/Objetos/Info';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-ver-anuncios',
  templateUrl: './ver-anuncios.component.html',
  styleUrls: ['./ver-anuncios.component.css']
})
export class VerAnunciosComponent implements OnInit {

  //Variables auxiliares
  mensaje: Info = new Info(false, "Error", "Error de conexión");
  espera: boolean = false;

  //Variables Lógicas
  anuncios: Anuncio[];

  constructor(
    private obtener: ObtenerObjetosService,
    private registar: RegistrarService
  ) {
    this.obtener.obtenerAnuncios().subscribe((respuesta: Anuncio[]) => {
      this.anuncios = respuesta;
    },
      (error: any) => {
        this.mensaje = error.error;
        this.espera = true;
      }
    );
  }

  ngOnInit(): void { }


  cambiarEstadoAnuncio(anuncio: Anuncio) {
    let a = anuncio.activo;
    anuncio.activo = !a;
    this.registar.registrarCambioAnuncio(anuncio).subscribe((respuesta: Info)=>{
      this.mensaje = respuesta;
      this.espera = true;
    },
      (error: any)=>{
        this.mensaje = error.error;
        this.espera = true;
      }
    );
  }
}
