ALTER TABLE tb_telephone ADD COLUMN maintainer_id bigint NOT NULL;
ALTER TABLE tb_telephone ADD CONSTRAINT fk_maintainer FOREIGN KEY (maintainer_id) REFERENCES tb_maintainer(id);
ALTER TABLE tb_telephone Add CONSTRAINT uk_maintainer UNIQUE (maintainer_id);