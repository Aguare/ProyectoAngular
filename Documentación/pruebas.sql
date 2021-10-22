USE AppRevistas;
SELECT * FROM Usuario WHERE nombre_usuario = 'admin';
SELECT * FROM TipoUsuario;
SELECT * FROM Usuario;
SELECT * FROM Perfil;
DESCRIBE Perfil;
SELECT * FROM Perfil_Etiquetas;
SELECT * FROM Anunciante;
SELECT * FROM Etiqueta;
SELECT * FROM Revista_Etiquetas;
DESCRIBE Revista;
INSERT INTO Revista (revista, titulo, descripcion, no_version, precio_costo,aprobado,suscripciones, precio_suscripcion, es_pago, tiene_comentarios, tiene_reacciones,fecha, R_nombre_usuario) VALUES("pathRevista", "Titulo", "debe tener descripcion", 1,1, 0,1, 0, 0, true,true,"2021-10-13","editor");
SELECT idRevista, RE_nombre_etiqueta FROM Revista INNER JOIN Revista_Etiquetas ON Revista.idRevista = Revista_Etiquetas.RE_idRevista;
SELECT * FROM Revista WHERE R_nombre_usuario = "editor";
SELECT * FROM Revista ORDER BY idRevista DESC;
SELECT * FROM Usuario;
SELECT * FROM Revista_Etiquetas WHERE RE_idRevista = 2;
SELECT * FROM Reaccion_Revista WHERE RR_idRevista = 1 AND RR_nombre_usuario = "lector";
SELECT * FROM Reaccion;
SELECT * FROM Reaccion_Revista;
SELECT * FROM Comentario ORDER BY fecha;
DESCRIBE Comentario;
SELECT * FROM Revista WHERE EXISTS (SELECT S_idRevista FROM Suscripcion WHERE S_usuario_lector = "lector" AND fecha_final >= NOW());
SELECT * FROM Suscripcion WHERE fecha_final >= NOW() AND S_idRevista= 1;
SELECT * FROM Pago;
DESCRIBE CambioRevista;
ALTER TABLE CambioRevista MODIFY COLUMN idCambioRevista int AUTO_INCREMENT;
INSERT INTO CambioRevista(precio_costo, fecha_inicio, CR_idRevista, CR_nombre_usuario) VALUES (2,"2021-03-10",1,"editor");
UPDATE CambioRevista SET fecha_final = "2021-04-10" WHERE idCambioRevista = (SELECT idCambioRevista FROM CambioRevista WHERE CR_idRevista = 1 ORDER BY idCambioRevista DESC LIMIT 1);