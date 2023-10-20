-- sub_organization_id
ALTER TABLE public.adm_organization ADD parent_organization_id BIGINT DEFAULT 0::BIGINT NOT NULL;

-- update old values
UPDATE public.adm_role SET sub_organization_id = 0 WHERE sub_organization_id IS NULL;
UPDATE public.adm_user SET sub_organization_id = 0 WHERE sub_organization_id IS NULL;

ALTER TABLE public.adm_role ALTER COLUMN sub_organization_id SET DEFAULT 0, ALTER COLUMN sub_organization_id SET NOT NULL;
ALTER TABLE public.adm_user ALTER COLUMN sub_organization_id SET DEFAULT 0, ALTER COLUMN sub_organization_id SET NOT NULL;

-- demo first sub-organizations
INSERT INTO adm_organization (organization_id, parent_organization_id, org_name, org_description) VALUES
(2501, 2500, 'FedEx Branch 1', null),
(2502, 2500, 'FedEx Branch 2', null);

-- users
INSERT INTO adm_user (user_id, organization_id, sub_organization_id, full_name, email, password, address, unique_identification_code) VALUES
(2501, 2500, 2501, 'Root Branch 1', 'root.branch1@shipping.dev', '$2a$10$sXe9HqBwA1U3l/3HKUlYHetw83heXcLeP4UbKgIzaHuzvWiAt4uxO', null, '1234567891235'),
(2502, 2500, 2502, 'Root Branch 2', 'root.branch2@shipping.dev', '$2a$10$sXe9HqBwA1U3l/3HKUlYHetw83heXcLeP4UbKgIzaHuzvWiAt4uxO', null, '1234567891236');

-- roles
INSERT INTO adm_role (role_id, organization_id, sub_organization_id, name, description, hourly_fee) VALUES
-- role for main organization
(2500, 2500, 0, 'FedEx Root', null, 10000),
(2501, 2500, 2501, 'FedEx Branch 1 Root', null, 10000),
(2502, 2500, 2502, 'FedEx Branch 2 Root', null, 10000);

-- user-roles
INSERT INTO adm_user_role (user_role_id, role_id, user_id, entry_date) VALUES
(2500, 2500, 2500, '2000-01-01 00:00:00'),
(2501, 2501, 2501, '2000-01-01 00:00:00'),
(2502, 2502, 2502, '2000-01-01 00:00:00');

-- role-permissions
INSERT INTO adm_role_permission (role_permission_id, role_id, permission_id, create_permission, read_permission, update_permission, delete_permission) VALUES
-- root
(2500, 2500, 5100, true, true, true, true),
(2501, 2500, 5101, true, true, true, true),
(2502, 2500, 5102, true, true, true, true),
(2503, 2500, 5103, true, true, true, true),

-- root branch 1
(2504, 2501, 5100, true, true, true, true),
(2505, 2501, 5101, true, true, true, true),
(2506, 2501, 5102, true, true, true, true),
(2507, 2501, 5103, true, true, true, true),

(2508, 2502, 5100, true, true, true, true),
(2509, 2502, 5101, true, true, true, true),
(2510, 2502, 5102, true, true, true, true),
(2511, 2502, 5103, true, true, true, true);

-- alter sequence SEQ_ADM_ORGANIZATION
ALTER SEQUENCE SEQ_ADM_ORGANIZATION INCREMENT 1 START WITH 5000;
