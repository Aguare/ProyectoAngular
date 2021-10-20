import { Etiqueta } from "./Etiqueta";
import { Usuario } from "./Usuario";

export class Revista {

    idRevista: number;
    revista: string;
    titulo: string;
    descripcion: string;
    no_version: number;
    precio_costo: number;
    aprobado: boolean;
    suscripciones: boolean;
    precio_suscripcion: number;
    es_pago: boolean;
    tiene_comentarios: boolean;
    tiene_reacciones: boolean;
    fecha: string;
    usuarioCreador: Usuario;
    etiquetas: Etiqueta[];

    constructor(
        idRevis: number,
        revis: string,
        titu: string,
        descrip: string,
        no_versio: number,
        precio_cost: number,
        apro: boolean,
        suscrip: boolean,
        precio_suscripcio: number,
        es_pag: boolean,
        tiene_comentario: boolean,
        tiene_reaccione: boolean,
        fech: string,
        usuarioCreado: Usuario,
        etiquet: Etiqueta[]
    ) {
        this.idRevista = idRevis;
        this.revista = revis;
        this.titulo = titu;
        this.descripcion = descrip;
        this.no_version = no_versio;
        this.precio_costo = precio_cost;
        this.aprobado = apro;
        this.suscripciones = suscrip;
        this.precio_suscripcion = precio_suscripcio;
        this.es_pago = es_pag;
        this.tiene_comentarios = tiene_comentario;
        this.tiene_reacciones = tiene_reaccione;
        this.usuarioCreador = usuarioCreado;
        this.fecha = fech;
        this.etiquetas = etiquet;
    }
}
