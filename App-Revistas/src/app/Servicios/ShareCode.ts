import { Usuario } from "../Objetos/Usuario";
import { AlmacenamientoLocalService } from "./Almacenamiento/AlmacenamientoLocal.service";

export class ShareCode {
    usuario: Usuario | null = null;

    constructor(public almacenLS: AlmacenamientoLocalService) { }

    deTodo() {
        //Castear JSON a objeto 
        this.usuario = JSON.parse(`${this.almacenLS.obtener("Usuario")}`);
    }

}