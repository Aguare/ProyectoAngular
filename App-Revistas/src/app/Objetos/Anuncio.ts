export class Anuncio {

    idAnuncio: number;
    tipo_anuncio: number;
    texto: string;
    video_url: string;
    imagen_path: string;
    activo: boolean;
    fecha_inicio: string;
    fecha_final: string;
    pago: number;
    A_nombre_anunciante: string;

    constructor(
        idAnuncio: number,
        tipo_anuncio: number,
        texto: string,
        video_url: string,
        imagen_path: string,
        activo: boolean,
        fecha_inicio: string,
        fecha_final: string,
        pago: number,
        A_nombre_anunciante: string,
    ) {
        this.idAnuncio = idAnuncio;
        this.tipo_anuncio = tipo_anuncio;
        this.texto = texto;
        this.video_url = video_url;
        this.imagen_path = imagen_path;
        this.activo = activo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.pago = pago;
        this.A_nombre_anunciante = A_nombre_anunciante;
    }
}
