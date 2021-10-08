import { Injectable } from '@angular/core';

function obtenerLS() {
  return localStorage;
}

@Injectable({
  providedIn: 'root',
})

// CREATE A GLOBAL LOCAL STORAGE USING SERVICES
export class AlmacenamientoPrincipalService {
  constructor() { }

  // RETURN STORAGE
  getAlmacenamientoLocal() {
    return obtenerLS();
  }
}
