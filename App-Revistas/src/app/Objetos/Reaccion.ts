export class Reaccion {

    idReaccion: number;
    reaccion: boolean;
    fecha: string;
    nombreUsuario: string;
    idRevista: number;

    constructor(
        idR: number,
        reac: boolean,
        fech: string,
        nombreU: string,
        idRev: number
    ){
        this.idReaccion = idR;
        this.reaccion = reac;
        this.fecha = fech;
        this.nombreUsuario = nombreU;
        this.idRevista = idRev;
    }
}
