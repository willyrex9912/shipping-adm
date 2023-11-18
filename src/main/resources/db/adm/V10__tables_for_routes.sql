CREATE TABLE public.adm_org_route (
    org_route_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    sub_organization_id BIGINT NOT NULL,
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
    CONSTRAINT adm_org_route_step_pk PRIMARY KEY (org_route_step_id)
);

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

CREATE SEQUENCE SEQ_ADM_ORG_ROUTE INCREMENT 1 START 5000;
CREATE SEQUENCE SEQ_ADM_ORG_ROUTE_STEP INCREMENT 1 START 5000;
