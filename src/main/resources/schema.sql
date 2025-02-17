create table if not exists app_user (
    id bigint auto_increment primary key,
    username varchar(255),
    password varchar(255),
    role varchar(255)
);

create table if not exists project (
    id bigint auto_increment primary key,
    name varchar(255),
    description varchar(255),
    user_id bigint,
    foreign key (user_id) references app_user(id) on delete cascade
);

create table if not exists photos (
    id bigint auto_increment primary key,
    file_name varchar(255),
    content_type varchar(255),
    data blob,
    project_id bigint,
    foreign key (project_id) references project(id) on delete cascade
);

create table if not exists threeDModel (
    id bigint auto_increment primary key,
    file_name varchar(255),
    content_type varchar(255),
    data blob,
    project_id bigint,
    foreign key (project_id) references project(id) on delete cascade
);

create table if not exists rating (
    id bigint auto_increment primary key,
    score int not null,
    comment varchar(255),
    user_id bigint not null,
    project_id bigint not null,
    version bigint default 0,
    foreign key (user_id) references app_user(id),
    foreign key (project_id) references project(id)
);