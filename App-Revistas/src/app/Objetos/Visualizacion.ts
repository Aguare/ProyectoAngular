export class Visualizacion {

    idVisualizacion: number;
    url: string;
    fecha: string;
    V_idAnuncio: number;
    V_nombre_anunciante: string;

    constructor(
        idVisualizacion: number,
        url: string,
        fecha: string,
        V_idAnuncio: number,
        V_nombre_anunciante: string
    ) {
        this.idVisualizacion = idVisualizacion;
        this.url = url;
        this.fecha = fecha;
        this.V_idAnuncio = V_idAnuncio;
        this.V_nombre_anunciante = V_nombre_anunciante;
    }
}
