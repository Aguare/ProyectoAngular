export class Comentario {

    idComentario: number;
    comentario: string;
    fecha: string;
    nombreUsuario: string;
    idRevista: number;

    constructor(
        idComentario: number,
        comentario: string,
        fecha: string,
        nombreUsuario: string,
        idRevista: number
    ) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fecha = fecha;
        this.nombreUsuario = nombreUsuario;
        this.idRevista = idRevista;
    }
}
