import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Backend } from '../Objetos/Backend';

@Injectable({
  providedIn: 'root'
})
export class RedireccionarService {

  constructor(public redireccionar: Router) { }

  enviarPagina(direccion: string) {
    this.redireccionar.navigate([direccion]);
  }
}
