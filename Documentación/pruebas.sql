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
SELECT * FROM ValoresSistema;
SELECT * FROM Suscripcion;
SELECT * FROM Revista WHERE idRevista = EXISTS (SELECT S_idRevista FROM Suscripcion WHERE S_usuario_lector = "lector" AND fecha_final >= NOW());
SELECT * FROM Revista;
SELECT * FROM Anuncio;
SELECT * FROM Anuncio_Etiquetas;
INSERT INTO Anunciante VALUES("PEPE",51806388);
INSERT INTO Anuncio(tipo_anuncio,texto,video_url,imagen_path,activo,fecha_inicio,fecha_final,pago,A_nombre_anunciante) VALUES(1,"TEXTO","VIDEO","IMAGEN",0,"2021-10-25","2021-10-26",800,"PEPE");
UPDATE Anuncio SET activo = 1 WHERE idAnuncio = 2;
SELECT * FROM Anuncio_Etiquetas WHERE AE_nombre_etiqueta = 'CUNOC' AND 'Ingeniería' AND 'Real Madrid';
SELECT AE_idAnuncio FROM Anuncio_Etiquetas WHERE AE_nombre_etiqueta IN('CUNOC', 'Ingeniería', 'Real Madrid') GROUP BY AE_idAnuncio;
SELECT RE_idRevista FROM Revista_Etiquetas WHERE RE_nombre_etiqueta IN ('CUNOC') GROUP BY RE_idRevista;
SELECT * FROM Revista WHERE aprobado = 0 AND idRevista IN (SELECT RE_idRevista FROM Revista_Etiquetas WHERE RE_nombre_etiqueta IN ('CUNOC') GROUP BY RE_idRevista);
SELECT * FROM Anuncio WHERE EXISTS (SELECT * FROM Anuncio_Etiquetas WHERE AE_nombre_etiqueta = 'CUNOC');
SELECT * FROM Etiqueta;
SELECT idRevista FROM Revista WHERE aprobado = 1 AND idRevista IN (SELECT RE_idRevista FROM Revista_Etiquetas WHERE RE_nombre_etiqueta IN ('Comida','Computadoras','Audifonos','Ingeniería','Cocina','Base de Datos','Mouse','Silla','Monitor','Perro','PepePollo','Mesa','CUNOC') GROUP BY RE_idRevista);