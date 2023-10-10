CREATE TABLE public.category (
    category_id BIGINT NOT NULL,
    parent_category_id BIGINT,
    internal_id BIGINT NOT NULL,
    description VARCHAR(100) NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (category_id)
);

CREATE TABLE public.permission (
    permission_id BIGINT NOT NULL,
    parent_permission_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    sref VARCHAR(100) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    priority INTEGER DEFAULT 0 NOT NULL,
    CONSTRAINT permission_pk PRIMARY KEY (permission_id)
);

CREATE TABLE public.role (
    role_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(200),
    hourly_fee NUMERIC(10,2) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (role_id)
);


CREATE TABLE public.role_permission (
    role_permission_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    create_permission BOOLEAN DEFAULT FALSE NOT NULL,
    read_permission BOOLEAN DEFAULT FALSE NOT NULL,
    update_permission BOOLEAN DEFAULT FALSE NOT NULL,
    delete_permission BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT role_permission_pk PRIMARY KEY (role_permission_id)
);

CREATE TABLE public.user (
    user_id BIGINT NOT NULL,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(500) NOT NULL,
    address VARCHAR(150),
    unique_identification_code VARCHAR(50) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (user_id)
);

CREATE TABLE public.working_day (
    working_day_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    entry_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT working_day_pk PRIMARY KEY (working_day_id)
);

CREATE TABLE public.token_credential (
    token_credential_id BIGINT NOT NULL,
    token VARCHAR(1024) NOT NULL,
    entry_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    user_agent VARCHAR(250) DEFAULT '' NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT token_credential_pk PRIMARY KEY (token_credential_id)
);

CREATE TABLE public.user_role (
    user_role_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    entry_date DATE NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (user_role_id)
);

CREATE INDEX category_index ON public.category USING BTREE(internal_id);

ALTER TABLE public.category ADD CONSTRAINT category_category_fk
FOREIGN KEY (parent_category_id)
REFERENCES public.category (category_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.permission ADD CONSTRAINT permission_permission_fk
FOREIGN KEY (parent_permission_id)
REFERENCES public.permission (permission_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.role_permission ADD CONSTRAINT permission_role_permission_fk
FOREIGN KEY (permission_id)
REFERENCES public.permission (permission_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.user_role ADD CONSTRAINT role_user_role_fk
FOREIGN KEY (role_id)
REFERENCES public.role (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.role_permission ADD CONSTRAINT role_role_permission_fk
FOREIGN KEY (role_id)
REFERENCES public.role (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.user_role ADD CONSTRAINT user_user_role_fk
FOREIGN KEY (user_id)
REFERENCES public.user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.token_credential ADD CONSTRAINT user_token_credential_fk
FOREIGN KEY (user_id)
REFERENCES public.user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.working_day ADD CONSTRAINT user_working_day_fk
FOREIGN KEY (user_id)
REFERENCES public.user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

CREATE SEQUENCE SEQ_CATEGORY INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ROLE INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_PERMISSION INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ROLE_PERMISSION INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_USER INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_USER_ROLE INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_WORKING_DAY INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_TOKEN_CREDENTIAL INCREMENT 1 START 5000;
