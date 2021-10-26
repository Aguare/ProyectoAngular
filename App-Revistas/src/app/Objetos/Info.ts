export class Info {

    operacion: boolean;
    tipoError: string;
    mensaje: string;

    constructor(
        operacion: boolean,
        tipoError: string,
        mensaje: string
    ) {
        this.tipoError = tipoError;
        this.mensaje = mensaje;
        this.operacion = operacion;
    }
}
