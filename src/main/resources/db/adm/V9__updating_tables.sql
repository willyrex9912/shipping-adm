-- adm_organization
ALTER TABLE public.adm_organization ADD COLUMN longitude NUMERIC(15, 10) DEFAULT 0::NUMERIC(15, 10) NOT NULL;
ALTER TABLE public.adm_organization ADD COLUMN latitude NUMERIC(15, 10) DEFAULT 0::NUMERIC(15, 10) NOT NULL;
ALTER TABLE public.adm_organization ADD COLUMN address CHARACTER VARYING(250) DEFAULT ''::CHARACTER VARYING NOT NULL;
ALTER TABLE public.adm_organization ADD COLUMN telephone_number CHARACTER VARYING(20) DEFAULT ''::CHARACTER VARYING NOT NULL;
ALTER TABLE public.adm_organization ADD COLUMN email CHARACTER VARYING(50) DEFAULT ''::CHARACTER VARYING NOT NULL;

-- adm_user
ALTER TABLE public.adm_user ADD COLUMN telephone_number CHARACTER VARYING(20) DEFAULT ''::CHARACTER VARYING NOT NULL;

CREATE TABLE public.adm_operation_cost (
    operation_cost_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    sub_organization_id BIGINT NOT NULL,
    category_cost BIGINT NOT NULL,
    amount NUMERIC (20, 6) DEFAULT 0::NUMERIC NOT NULL,
    end_date TIMESTAMP WITHOUT TIME ZONE DEFAULT '1900-01-01 00:00:00'::TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT adm_operation_cost_pk PRIMARY KEY (operation_cost_id)
);

CREATE TABLE public.adm_vehicle (
    vehicle_id BIGINT NOT NULL,
    organization_id BIGINT NULL,
    capacity NUMERIC(5, 2) NOT NULL,
    avg_cost_per_km NUMERIC(20, 6) NOT NULL,
    category_status BIGINT NOT NULL,
    CONSTRAINT adm_vehicle_pk PRIMARY KEY (vehicle_id)
);

CREATE TABLE public.adm_package (
    package_id BIGINT NOT NULL,
    weight NUMERIC (5, 2) NOT NULL,
    description CHARACTER VARYING (250) DEFAULT ''::CHARACTER VARYING NOT NULL,
    entry_date TIMESTAMP WITHOUT TIME ZONE DEFAULT '1900-01-01 00:00:00'::TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    estimated_cost NUMERIC (20, 6) NOT NULL,
    source_organization_id BIGINT NOT NULL,
    target_organization_id BIGINT NOT NULL,
    source_customer_name CHARACTER VARYING (100) DEFAULT ''::CHARACTER VARYING NOT NULL,
    source_customer_contact CHARACTER VARYING (150) DEFAULT ''::CHARACTER VARYING NOT NULL,
    target_customer_name CHARACTER VARYING (100) DEFAULT ''::CHARACTER VARYING NOT NULL,
    target_customer_contact CHARACTER VARYING (150) DEFAULT ''::CHARACTER VARYING NOT NULL,
    package_code CHARACTER VARYING (150) DEFAULT ''::CHARACTER VARYING NOT NULL,
    CONSTRAINT adm_package_pk PRIMARY KEY (package_id)
);

-- vehicle
ALTER TABLE public.adm_vehicle ADD CONSTRAINT adm_organization_adm_vehicle_fk
FOREIGN KEY (organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

-- shipping package
ALTER TABLE public.adm_package ADD CONSTRAINT adm_organization_source_id_adm_package_pk
FOREIGN KEY (source_organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_package ADD CONSTRAINT adm_organization_target_id_adm_package_pk
FOREIGN KEY (target_organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
