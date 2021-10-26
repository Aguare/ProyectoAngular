package Controlador;

import SQL.ErrorSQL;

/**
 *
 * @author marco
 */
public class ModificarError {

    public String obtenerTipoError(int error) {
        switch (error) {
            case ErrorSQL.REGISTRO_EXISTE:
                return "<- El registro ya existe";
            case ErrorSQL.DATOS_INCOMPLETOS:
                return "<- Los datos no estan completos";
            case ErrorSQL.FORMATO_INCORRECTO:
                return "<- Los datos no cumplen con su formato";
            case ErrorSQL.ERROR_NUMERICO:
                return "<- Los valores numéricos contienen un error";
            case ErrorSQL.ID_NOEXISTE:
                return "<- El número de ID al que desea hacer referencia no existe";
            case ErrorSQL.ERROR_NIT:
                return "<- El NIT no debe contener guiones";
            case ErrorSQL.ERROR_INSERTAR:
                return "<- No se pudo cargar a la BD";
            case ErrorSQL.DATOS_VACIOS:
                return "<- Los datos no pueden estar vacios";
            case ErrorSQL.SOBREPASA:
                return "<- El tamaño de los datos sobrepasa el límite";
            default:
                return "<- No se pudo insertar a la Base de Datos";
        }
    }
}
