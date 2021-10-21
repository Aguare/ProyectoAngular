export class ValorSistema {

    porcentaje_comision: number;
    fecha: string;

    constructor(
        porcentaje: number,
        fecha: string
    ) {
        this.porcentaje_comision = porcentaje;
        this.fecha = fecha;
    }
}
