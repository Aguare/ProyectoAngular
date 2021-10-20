DROP DATABASE IF EXISTS AppRevistas;
CREATE DATABASE IF NOT EXISTS AppRevistas;
USE AppRevistas;

CREATE TABLE ValoresSistema(
    idValores INT NOT NULL,
    porcentaje_comision DOUBLE NOT NULL,
    fecha DATE NOT NULL
);

CREATE TABLE TipoUsuario(
    idTipoUsuario INT NOT NULL AUTO_INCREMENT,
    nombre_tipo VARCHAR(13) NOT NULL,
    PRIMARY KEY(idTipoUsuario)
);

CREATE TABLE Usuario(
    nombre_usuario VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    U_idTipoUsuario INT NOT NULL,
    PRIMARY KEY(nombre_usuario),
    FOREIGN KEY(U_idTipoUsuario) REFERENCES TipoUsuario(idTipoUsuario)
);

CREATE TABLE Perfil(
    idPerfil INT NOT NULL AUTO_INCREMENT,
    foto TEXT,
    descripcion VARCHAR(100),
    P_nombre_usuario VARCHAR(50) NOT NULL,
    PRIMARY KEY(idPerfil),
    FOREIGN KEY(P_nombre_usuario) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Etiqueta(
    nombre_etiqueta VARCHAR(50) NOT NULL,
    PRIMARY KEY(nombre_etiqueta)
);

CREATE TABLE Categoria(
    nombre_categoria VARCHAR(50) NOT NULL,
    PRIMARY KEY(nombre_categoria)
);

CREATE TABLE Perfil_Categorias(
    PC_nombre_categoria VARCHAR(50) NOT NULL,
    PC_idPerfil INT NOT NULL,
    PC_nombre_usuario VARCHAR(50) NOT NULL,
    FOREIGN KEY(PC_nombre_categoria) REFERENCES Categoria(nombre_categoria),
    FOREIGN KEY(PC_idPerfil) REFERENCES Perfil(idPerfil),
    FOREIGN KEY(PC_nombre_usuario) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Perfil_Etiquetas(
    PE_idperfil INT NOT NULL,
    PE_nombre_usuario VARCHAR(50) NOT NULL,
    PE_nombre_etiqueta VARCHAR(50) NOT NULL,
    FOREIGN KEY(PE_idperfil) REFERENCES Perfil(idPerfil),
    FOREIGN KEY(PE_nombre_usuario) REFERENCES Usuario(nombre_usuario),
    FOREIGN KEY(PE_nombre_etiqueta) REFERENCES Etiqueta(nombre_etiqueta)
);

CREATE TABLE Revista(
    idRevista INT NOT NULL AUTO_INCREMENT,
    revista TEXT NOT NULL,
    titulo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(150) NOT NULL,
    no_version INT,
    precio_costo DOUBLE,
    aprobado TINYINT NOT NULL,
    suscripciones TINYINT NOT NULL,
    precio_suscripcion DOUBLE NOT NULL,
    es_pago TINYINT NOT NULL,
    tiene_comentarios TINYINT NOT NULL,
    tiene_reacciones TINYINT NOT NULL,
    fecha DATE NOT NULL,
    R_nombre_usuario VARCHAR(50) NOT NULL,
    PRIMARY KEY(idRevista),
    FOREIGN KEY(R_nombre_usuario) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Comentario(
    idComentario INT NOT NULL AUTO_INCREMENT,
    comentario VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    C_nombre_usuario VARCHAR(50) NOT NULL,
    PRIMARY KEY(idComentario),
    FOREIGN KEY(C_nombre_usuario) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Reaccion(
    idReaccion INT NOT NULL AUTO_INCREMENT,
    reaccion TINYINT NOT NULL,
    fecha DATE NOT NULL,
    RM_nombre_usuario VARCHAR(50) NOT NULL,
    PRIMARY KEY(idReaccion),
    FOREIGN KEY(RM_nombre_usuario) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Revista_Etiquetas(
    RE_idRevista INT NOT NULL,
    RE_nombre_etiqueta VARCHAR(50) NOT NULL,
    FOREIGN KEY(RE_idRevista) REFERENCES Revista(idRevista),
    FOREIGN KEY(RE_nombre_etiqueta) REFERENCES Etiqueta(nombre_etiqueta)
);

CREATE TABLE Revista_Categorias(
    RC_idRevista INT NOT NULL,
    RC_nombre_categoria VARCHAR(50) NOT NULL,
    FOREIGN KEY(RC_idRevista) REFERENCES Revista(idRevista),
    FOREIGN KEY(RC_nombre_categoria) REFERENCES Categoria(nombre_categoria)
);

CREATE TABLE Comentario_Revista(
    CR_idComentario INT NOT NULL,
    CR_nombre_usuario VARCHAR(50) NOT NULL,
    CR_idRevista INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY(CR_idComentario) REFERENCES Comentario(idComentario),
    FOREIGN KEY(CR_nombre_usuario) REFERENCES Usuario(nombre_usuario),
    FOREIGN KEY(CR_idRevista) REFERENCES Revista(idRevista)
);

CREATE TABLE Reaccion_Revista(
    RR_idReaccion INT NOT NULL,
    RR_nombre_usuario VARCHAR(50),
    RR_idRevista INT NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY(RR_idReaccion) REFERENCES Reaccion(idReaccion),
    FOREIGN KEY(RR_nombre_usuario) REFERENCES Usuario(nombre_usuario),
    FOREIGN KEY(RR_idRevista) REFERENCES Revista(idRevista)
);

CREATE TABLE CambioRevista(
    idCambioRevista INT NOT NULL,
    precio_costo DOUBLE NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE,
    CR_idRevista INT NOT NULL,
    CR_nombre_usuario VARCHAR(50) NOT NULL,
    PRIMARY KEY(idCambioRevista),
    FOREIGN KEY(CR_idRevista) REFERENCES Revista(idRevista),
    FOREIGN KEY(CR_nombre_usuario) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Suscripcion(
    idSuscripcion INT NOT NULL AUTO_INCREMENT,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE NOT NULL,
    meses INT NOT NULL,
    esta_anulado TINYINT NOT NULL,
    S_usuario_lector VARCHAR(50) NOT NULL,
    S_idRevista INT NOT NULL,
    S_usuario_editor VARCHAR(50) NOT NULL,
    PRIMARY KEY(idSuscripcion),
    FOREIGN KEY(S_usuario_lector) REFERENCES Usuario(nombre_usuario),
    FOREIGN KEY(S_idRevista) REFERENCES Revista(idRevista),
    FOREIGN KEY(S_usuario_editor) REFERENCES Usuario(nombre_usuario)
);

CREATE TABLE Pago(
    idPago INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    parte_obtenido DOUBLE NOT NULL,
    parte_editor DOUBLE NOT NULL,
    total DOUBLE NOT NULL,
    P_idSuscripcion INT NOT NULL,
    PRIMARY KEY(idPago),
    FOREIGN KEY(P_idSuscripcion) REFERENCES Suscripcion(idSuscripcion)
);

CREATE TABLE Anunciante(
    nombre_anunciante VARCHAR(50) NOT NULL,
    telefono INT,
    PRIMARY KEY(nombre_anunciante)
);

CREATE TABLE Anuncio(
    idAnuncio INT NOT NULL,
    tipo_anuncio INT NOT NULL,
    texto TEXT,
    video_url TEXT,
    imagen_path TEXT,
    A_nombre_anunciante VARCHAR(50) NOT NULL,
    PRIMARY KEY(idAnuncio),
    FOREIGN KEY(A_nombre_anunciante) REFERENCES Anunciante(nombre_anunciante)
);

CREATE TABLE Visualizacion(
    idVisualizacion INT NOT NULL AUTO_INCREMENT,
    url TEXT NOT NULL,
    V_idAnuncio INT NOT NULL,
    V_nombre_anunciante VARCHAR(50) NOT NULL,
    PRIMARY KEY(idVisualizacion),
    FOREIGN KEY(V_idAnuncio) REFERENCES Anuncio(idAnuncio),
    FOREIGN KEY(V_nombre_anunciante) REFERENCES Anunciante(nombre_anunciante)
);

INSERT INTO TipoUsuario(nombre_tipo) VALUES("Administrador");
INSERT INTO TipoUsuario(nombre_tipo) VALUES("Lector");
INSERT INTO TipoUsuario(nombre_tipo) VALUES("Editor");

INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Ropa");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Comida");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Carros");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Animales");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Perro");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Cocina");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Carpinteria");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Tecnología");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Computadoras");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Monitor");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Mouse");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Silla");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Mesa");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Audifonos");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Case");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("ventilador");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Gorra");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Real Madrid");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Barcelona");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("PSG");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Messi");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Cristiano Ronaldo");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Programación");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("USAC");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("CUNOC");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Ingeniería");
INSERT INTO Etiqueta(nombre_etiqueta) VALUES("Base de Datos");