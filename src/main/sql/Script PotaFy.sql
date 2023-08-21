drop database if exists PotaFy;
create database PotaFy;
use PotaFy;

create table usuario (
	idUsuario int auto_increment,
    nombre varchar(25),
    apellidos varchar(50),
    nombreUsuario varchar(20) unique not null,
    clave varchar(15),
    fechaNacimiento date,
    email varchar(30),
    deBaja boolean default false,
    constraint PK_USUARIO primary key (idUsuario)
);

create table album (
	idAlbum int auto_increment,
    nombre varchar(30),
    portada varchar(255),
    interprete varchar(50),
    fechaPublicacion year,
    constraint PK_ALBUM primary key (idAlbum)
);

create table cancion (
	idCancion int auto_increment,
    titulo varchar(30),
    duracion time,
    genero varchar(20),
    urlFichero varchar(255),
    publica boolean,
    idAlbum int,
    idUsuario int,
    constraint PK_CANCION primary key (idCancion),
    constraint FK_CANCION_USUARIO foreign key (idUsuario)
		references usuario(idUsuario),
	constraint FK_CANCION_ALBUM foreign key (idAlbum)
		references album(idAlbum)
);

create table playList(
	idPlayList int auto_increment,
    nombre varchar(20),
    idUsuario int,
    constraint UNIQUE_PLAYLIST_NOMBRE unique(nombre, idUsuario),
    constraint PK_PLAYLIST primary key (idPlayList),
    constraint FK_PLAYLIST_USUARIO foreign key (idUsuario)
		references usuario(idUsuario)
);

create table playListCancion (
	idPlayList int,
    idCancion int,
    constraint PK_PLAYLISTCANCION primary key (idPlayList, idCancion),
    constraint FK_PLAYLISTCANCION_PLAYLIST foreign key (idPlayList)
		references playList(idPlayList),
    constraint FK_PLAYLISTCANCION_CANCION foreign key (idCancion)
		references cancion(idCancion)
);