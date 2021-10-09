export class Usuario {

    tipoUsuario: string;
    nombreUsuario: string;
    password: string;

    constructor(
        tipoUsuario: string,
        nombreUsuario: string,
        password: string
    ) {
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
}