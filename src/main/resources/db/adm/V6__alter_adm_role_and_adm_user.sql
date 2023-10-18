ALTER TABLE public.adm_role
    ADD COLUMN organization_id BIGINT NOT NULL;

ALTER TABLE public.adm_user_role DROP COLUMN organization_id;

ALTER TABLE public.adm_role
    ADD CONSTRAINT adm_organization_adm_role_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.adm_organization (organization_id);

ALTER TABLE public.adm_user
    ADD CONSTRAINT adm_organization_adm_user_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.adm_organization (organization_id);