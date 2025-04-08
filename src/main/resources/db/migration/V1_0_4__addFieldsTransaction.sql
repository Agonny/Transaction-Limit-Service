alter table public.transaction add column if not exists datetime timestamp(2);
alter table public.transaction add column if not exists limit_exceeded boolean default false not null;

alter table limit_remainder drop column if exists limit_exceeded;