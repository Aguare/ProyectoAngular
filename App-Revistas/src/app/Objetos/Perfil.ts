import { Etiqueta } from "./Etiqueta";

export class Perfil {

    descripcion: string;
    etiquetasInteres: Etiqueta[];

    constructor(descripcion:string, etiquetas: Etiqueta[]){
        this.descripcion = descripcion;
        this.etiquetasInteres = etiquetas;
    }
}
