create table limit (
    id bigint primary key,
    category nvarchar(100) not null,
    record_time timestamp(2) not null,
    value float not null,
);

create table limit_remainder (
    id bigint primary key,
    limit_id bigint references limit(id),
    limit_exceeded boolean not null default false,
    remainder float not null
);

create table transaction (
    id bigint primary key,
    account_from bigint not null,
    account_to bigint not null,
    currency_shortname nvarchar(20) not null,
    expense_category nvarchar(100) not null,
    sum float not null,
    limit_remainder_id bigint references limit_remainder(id)
);
