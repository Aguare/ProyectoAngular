import { Etiqueta } from "./Etiqueta";

export class Perfil {

    foto: string;
    descripcion: string;
    etiquetasInteres: Etiqueta[];

    constructor(
        foto: string,
        descripcion: string,
        etiquetas: Etiqueta[]
    ) {
        this.foto = foto;
        this.descripcion = descripcion;
        this.etiquetasInteres = etiquetas;
    }
}
