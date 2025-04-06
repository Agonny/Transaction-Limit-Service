create table tr_limit (
    id bigint primary key,
    category varchar(100) not null,
    record_time timestamp(2) not null,
    value float not null
);

create table limit_remainder (
    id bigint primary key,
    limit_id bigint references tr_limit(id),
    limit_exceeded boolean default false not null,
    remainder float not null
);

create table transaction (
    id bigint primary key,
    account_from bigint not null,
    account_to bigint not null,
    currency_shortname varchar(20) not null,
    expense_category varchar(100) not null,
    sum float not null,
    limit_remainder_id bigint references limit_remainder(id)
);
