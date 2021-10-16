USE AppRevistas;
SELECT * FROM Usuario WHERE nombre_usuario = 'admin';
SELECT * FROM TipoUsuario;
SELECT * FROM Usuario;
SELECT * FROM Perfil;
SELECT * FROM Perfil_Etiquetas;
SELECT * FROM Anunciante;
SELECT * FROM Etiqueta;
SELECT * FROM Revista;
DESCRIBE Revista;
INSERT INTO Revista (revista, titulo, descripcion, no_version, precio_costo, precio_suscripcion, es_pago, tiene_comentarios, tiene_reacciones, R_nombre_usuario)
VALUES("pathRevista", "Titulo", "debe tener descripcion", 1, 0, 0, 0, 1,1,"editor");