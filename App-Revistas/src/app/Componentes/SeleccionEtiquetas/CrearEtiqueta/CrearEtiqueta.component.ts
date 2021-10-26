import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { ObtenerEtiquetasService } from 'src/app/Servicios/ObtenerObjetos/ObtenerEtiquetas.service';
import { RedireccionarService } from 'src/app/Servicios/Redireccionar.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';


@Component({
  selector: 'app-CrearEtiqueta',
  templateUrl: './CrearEtiqueta.component.html',
  styleUrls: ['./CrearEtiqueta.component.css']
})
export class CrearEtiquetaComponent implements OnInit {

  etiquetas: Etiqueta[];
  nuevaEtiqueta: string = "";
  error: boolean = false;
  mensaje: Info = new Info(false, "Sin espacios en blanco", "");
  @Output() incluir = new EventEmitter<string>();

  constructor(
    public obtenerEtiquetas: ObtenerEtiquetasService,
    private redireccionar: RedireccionarService,
    private registrar: RegistrarService
  ) {
    obtenerEtiquetas.obtenerEtiquetas().subscribe((exito: Etiqueta[]) => {
      this.etiquetas = exito;
    },
      error => redireccionar.enviarPagina("ErrorConexion")
    );
  }

  ngOnInit() {
  }

  verificarEspacio(evento: any) {
    this.nuevaEtiqueta = evento.target.value;
    this.nuevaEtiqueta = this.nuevaEtiqueta.trim();
  }

  agregarEtiqueta() {
    if (this.nuevaEtiqueta != "") {
      this.error = false;
      if (this.etiquetas != null) {
        this.error = this.verificarExistencia();
        if (!this.error) {
          this.registrar.registrarEtiqueta(this.nuevaEtiqueta).subscribe((respuesta: Info) => {
            this.mensaje = respuesta;
            this.error = true;
            this.incluirEtiqueta();
          },
          (error:any)=>{
            this.mensaje = error.error;
            this.error = true;
          }
          );
        } else {
          this.nuevaEtiqueta = "";
        }
      } else {
        this.mensaje = new Info(false, "Sin Conexión", "No tiene red");
      }
    } else {
      this.error = true;
      this.mensaje = new Info(false, "Etiqueta en Blanco", "No se admiten espacios");
    }
  }

  verificarExistencia(): boolean {
    for (let i = 0; i < this.etiquetas.length; i++) {
      const etiqueta = this.etiquetas[i];
      if (etiqueta.nombre.toUpperCase() == this.nuevaEtiqueta.toUpperCase()) {
        this.mensaje = new Info(false, "Ya existe", "Seleccione la etiqueta");
        return true;
      }
    }
    return false;
  }

  incluirEtiqueta() {
    this.incluir.emit(this.nuevaEtiqueta);
    this.nuevaEtiqueta = "";
  }
}
