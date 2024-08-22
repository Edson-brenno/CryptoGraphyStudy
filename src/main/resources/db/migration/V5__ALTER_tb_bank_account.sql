ALTER TABLE tb_bank_account ADD COLUMN maintainer_id bigint NOT NULL;
ALTER TABLE tb_bank_account ADD CONSTRAINT fk_maintainer FOREIGN KEY (maintainer_id) REFERENCES tb_maintainer(id);