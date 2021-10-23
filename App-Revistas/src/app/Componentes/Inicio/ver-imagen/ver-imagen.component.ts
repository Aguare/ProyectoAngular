import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Backend } from 'src/app/Objetos/Backend';

@Component({
  selector: 'app-ver-imagen',
  templateUrl: './ver-imagen.component.html',
  styleUrls: ['./ver-imagen.component.css']
})
export class VerImagenComponent implements OnInit {

  @Input() path: string;
  url: SafeResourceUrl;
  constructor(
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.obtenerLink();
  }

  obtenerLink() {
    let url = Backend.Path + "SubirArchivo?opcion=3&path=" + this.path;
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

}
