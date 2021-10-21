export class Pago {

    idPago: number;
    parte_obtenido: number;
    parte_editor: number;
    total: number;

    constructor(
        idPago: number,
        parte_obtenido: number,
        parte_editor: number,
        total: number
    ) {
        this.idPago = idPago;
        this.parte_obtenido = parte_obtenido;
        this.parte_editor = parte_editor;
        this.total = total;
    }
}
