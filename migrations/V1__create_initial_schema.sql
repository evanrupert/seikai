create table forms (
    id uuid primary key not null,
    name varchar(255) null
);

create table fields (
    id uuid primary key not null,
    formId uuid references forms(id) not null,
    name varchar(255) not null,
    type varchar(255) not null
);

create index formId_idx on fields (formId);
