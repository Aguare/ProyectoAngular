import { Pago } from "./Pago";
import { Usuario } from "./Usuario";

export class Suscripcion {

    idSuscripcion: number;
    fechaInicio: string;
    fechaFinal: string;
    meses: number;
    esta_anulado: boolean;
    lector: Usuario;
    idRevista: number;
    editor: Usuario;
    pago: Pago;

    constructor(
        idSuscripcion: number,
        fechaInicio: string,
        fechaFinal: string,
        meses: number,
        esta_anulado: boolean,
        lector: Usuario,
        idRevista: number,
        editor: Usuario,
        pago: Pago
    ) {
        this.idSuscripcion = idSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.meses = meses;
        this.esta_anulado = esta_anulado;
        this.lector = lector;
        this.idRevista = idRevista;
        this.editor = editor;
        this.pago = pago;
     }
}
