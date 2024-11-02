CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE event_publication (
    completion_date TIMESTAMP(6) WITH TIME ZONE,
    publication_date TIMESTAMP(6) WITH TIME ZONE,
    id UUID NOT NULL,
    event_type VARCHAR(255),
    listener_id VARCHAR(255),
    serialized_event VARCHAR(255),
    primary key (id)
);

create table peserta (
	id_peserta varchar(255) not null,
	fullname varchar(100) not null,
	jenis_kelamin varchar(50),
	tanggal_lahir date,
	alamat varchar(255),
	nomor_handphone varchar(15) not null,
	email varchar(255) not null,
	foto varchar(255),
	primary key (id_peserta)
);