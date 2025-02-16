create table if not exists photos (
    id bigint auto_increment primary key,
    file_name varchar(255),
    content_type varchar(255),
    data blob
);

create table if not exists threeDModel (
    id bigint auto_increment primary key,
    file_name varchar(255),
    content_type varchar(255),
    data blob
);

create table if not exists rating (
    id bigint auto_increment primary key,
    score int,
    comment varchar(255),
    model_reference varchar(255)
);

create table if not exists app_user (
    id bigint auto_increment primary key,
    username varchar(255),
    password varchar(255),
    role varchar(255)
);

create table if not exists project (
    id bigint auto_increment primary key,
    project_name varchar(255),
    description varchar(255)
);