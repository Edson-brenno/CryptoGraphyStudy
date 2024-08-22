CREATE TABLE tb_bank_account (
                                 id bigint GENERATED BY DEFAULT AS identity,
                                 name_bank text not null,
                                 agency text not null,
                                 account_number text not null,
                                 is_active boolean not null,

                                 primary key (id)
)