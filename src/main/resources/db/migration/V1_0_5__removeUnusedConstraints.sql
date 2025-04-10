drop sequence if exists transaction_seq;

alter table transaction drop constraint if exists transaction_limit_remainder_id_fkey;
alter table transaction drop column if exists limit_remainder_id;
