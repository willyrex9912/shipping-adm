CREATE TABLE public.adm_org_route (
    org_route_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    route_name CHARACTER VARYING (100) DEFAULT ''::CHARACTER VARYING NOT NULL,
    route_description CHARACTER VARYING (200) DEFAULT ''::CHARACTER VARYING NOT NULL,
    entry_date TIMESTAMP WITHOUT TIME ZONE DEFAULT '1900-01-01 00:00:00'::TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT adm_org_route_pk PRIMARY KEY (org_route_id)
);

CREATE TABLE public.adm_org_route_step (
    org_route_step_id BIGINT NOT NULL,
    org_route_id BIGINT NOT NULL,
    step_number INTEGER NOT NULL,
    source_organization_id BIGINT NOT NULL,
    target_organization_id BIGINT NOT NULL,
    average_distance NUMERIC (7, 2) DEFAULT 0::NUMERIC NOT NULL,
    CONSTRAINT adm_org_route_step_pk PRIMARY KEY (org_route_step_id)
);

CREATE TABLE public.adm_package_route (
    package_route_id BIGINT NOT NULL,
    vehicle_id BIGINT NULL,
    package_id BIGINT NOT NULL,
    step_number INTEGER NOT NULL,
    source_organization_id BIGINT NOT NULL,
    target_organization_id BIGINT NOT NULL,
    estimated_time NUMERIC (5, 2) DEFAULT 0::NUMERIC NOT NULL,
    estimated_cost NUMERIC (20, 6) DEFAULT 0::NUMERIC NOT NULL,
    entry_date TIMESTAMP WITHOUT TIME ZONE NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE NULL,
    end_date TIMESTAMP WITHOUT TIME ZONE NULL,
    category_package_route_status BIGINT NOT NULL,
    CONSTRAINT adm_package_route_pk PRIMARY KEY (package_route_id)
);

-- adm_org_route
ALTER TABLE public.adm_org_route ADD CONSTRAINT adm_organization_adm_org_route_fk
FOREIGN KEY (organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

-- adm_org_route_step
ALTER TABLE public.adm_org_route_step ADD CONSTRAINT adm_organization_source_adm_org_route_step_fk
FOREIGN KEY (source_organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_org_route_step ADD CONSTRAINT adm_organization_target_adm_org_route_step_fk
FOREIGN KEY (target_organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_org_route_step ADD CONSTRAINT adm_org_route_adm_org_route_step_fk
FOREIGN KEY (org_route_id)
REFERENCES public.adm_org_route (org_route_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

-- adm_package_route
ALTER TABLE public.adm_package_route ADD CONSTRAINT adm_vehicle_adm_package_route_fk
FOREIGN KEY (vehicle_id)
REFERENCES public.adm_vehicle (vehicle_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_package_route ADD CONSTRAINT adm_package_adm_package_route_fk
FOREIGN KEY (package_id)
REFERENCES public.adm_package (package_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_package_route ADD CONSTRAINT adm_organization_source_adm_package_route_fk
FOREIGN KEY (source_organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.adm_package_route ADD CONSTRAINT adm_organization_target_adm_package_route_fk
FOREIGN KEY (target_organization_id)
REFERENCES public.adm_organization (organization_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

-- categories for package route status
INSERT INTO public.adm_category (category_id, parent_category_id, internal_id, description) VALUES
(10520, NULL, 520, 'Package_Route_Status'),
(10521, 10520, 521, 'PRS_Registered'),
(10522, 10520, 522, 'PRS_Pending'),
(10523, 10520, 523, 'PRS_In_Transit'),
(10524, 10520, 524, 'PRS_Completed');

-- sequences
CREATE SEQUENCE SEQ_ADM_ORG_ROUTE INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_ORG_ROUTE_STEP INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_PACKAGE_ROUTE INCREMENT 1 START 5000;
