CREATE TABLE public.adm_category (
    category_id BIGINT NOT NULL,
    parent_category_id BIGINT,
    internal_id BIGINT NOT NULL,
    description VARCHAR(100) NOT NULL,
    CONSTRAINT adm_category_pk PRIMARY KEY (category_id)
);

CREATE TABLE public.adm_permission (
    permission_id BIGINT NOT NULL,
    parent_permission_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    sref VARCHAR(100) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    priority INTEGER DEFAULT 0 NOT NULL,
    CONSTRAINT adm_permission_pk PRIMARY KEY (permission_id)
);

CREATE TABLE public.adm_role (
    role_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(200),
    hourly_fee NUMERIC(10,2) NOT NULL,
    CONSTRAINT adm_role_pk PRIMARY KEY (role_id)
);


CREATE TABLE public.adm_role_permission (
    role_permission_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    create_permission BOOLEAN DEFAULT FALSE NOT NULL,
    read_permission BOOLEAN DEFAULT FALSE NOT NULL,
    update_permission BOOLEAN DEFAULT FALSE NOT NULL,
    delete_permission BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT adm_role_permission_pk PRIMARY KEY (role_permission_id)
);

CREATE TABLE public.adm_user (
    user_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(500) NOT NULL,
    address VARCHAR(150),
    unique_identification_code VARCHAR(50) NOT NULL,
    CONSTRAINT adm_user_pk PRIMARY KEY (user_id)
);

CREATE TABLE public.adm_working_day (
    working_day_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    entry_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT adm_working_day_pk PRIMARY KEY (working_day_id)
);

CREATE TABLE public.adm_token_credential (
    token_credential_id BIGINT NOT NULL,
    token VARCHAR(1024) NOT NULL,
    entry_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    user_agent VARCHAR(250) DEFAULT '' NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT adm_token_credential_pk PRIMARY KEY (token_credential_id)
);

CREATE TABLE public.adm_user_role (
    user_role_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    entry_date DATE NOT NULL,
    CONSTRAINT adm_user_role_pk PRIMARY KEY (user_role_id)
);

CREATE TABLE public.adm_organization (
    organization_id BIGINT NOT NULL,
    org_name VARCHAR(100) NOT NULL,
    org_description VARCHAR(150),
    CONSTRAINT adm_organization_pk PRIMARY KEY (organization_id)
);

CREATE INDEX adm_category_index ON public.adm_category USING BTREE(internal_id);

ALTER TABLE public.adm_category ADD CONSTRAINT adm_category_adm_category_fk
FOREIGN KEY (parent_category_id)
REFERENCES public.adm_category (category_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_permission ADD CONSTRAINT adm_permission_adm_permission_fk
FOREIGN KEY (parent_permission_id)
REFERENCES public.adm_permission (permission_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_role_permission ADD CONSTRAINT adm_permission_adm_role_permission_fk
FOREIGN KEY (permission_id)
REFERENCES public.adm_permission (permission_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_user_role ADD CONSTRAINT adm_role_adm_user_role_fk
FOREIGN KEY (role_id)
REFERENCES public.adm_role (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_role_permission ADD CONSTRAINT adm_role_adm_role_permission_fk
FOREIGN KEY (role_id)
REFERENCES public.adm_role (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_user_role ADD CONSTRAINT adm_user_adm_user_role_fk
FOREIGN KEY (user_id)
REFERENCES public.adm_user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_token_credential ADD CONSTRAINT adm_user_adm_token_credential_fk
FOREIGN KEY (user_id)
REFERENCES public.adm_user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_working_day ADD CONSTRAINT adm_user_adm_working_day_fk
FOREIGN KEY (user_id)
REFERENCES public.adm_user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

CREATE SEQUENCE SEQ_ADM_CATEGORY INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_ROLE INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_PERMISSION INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_ROLE_PERMISSION INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_USER INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_USER_ROLE INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_WORKING_DAY INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_TOKEN_CREDENTIAL INCREMENT 1 START 5000;
