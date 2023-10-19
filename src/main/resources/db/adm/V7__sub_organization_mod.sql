ALTER TABLE public.adm_role
    ADD COLUMN sub_organization_id BIGINT
;

ALTER TABLE public.adm_user
    ADD COLUMN sub_organization_id BIGINT
;