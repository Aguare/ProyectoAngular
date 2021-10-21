import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Backend } from 'src/app/Objetos/Backend';

@Component({
  selector: 'app-ver-revista',
  templateUrl: './ver-revista.component.html',
  styleUrls: ['./ver-revista.component.css']
})
export class VerRevistaComponent implements OnInit {

  @Input() path: string;
  url: SafeResourceUrl;

  constructor(
    private sanitizer: DomSanitizer
    ) {
  }

  ngOnInit(): void {
    this.obtenerLink();
  }

  obtenerLink() {
    let _showPdfUrl = Backend.Path + "SubirArchivo?opcion=1&path=" + this.path;
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(_showPdfUrl);
  }

}
