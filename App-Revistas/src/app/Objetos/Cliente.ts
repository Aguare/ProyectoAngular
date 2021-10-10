import { Perfil } from "./Perfil";
import { Usuario } from "./Usuario";

export class Cliente {

    usuario: Usuario;
    perfil: Perfil;

    constructor(
        usuario: Usuario,
        perfil: Perfil
    ) {
        this.usuario = usuario;
        this.perfil = perfil;
    }
}
