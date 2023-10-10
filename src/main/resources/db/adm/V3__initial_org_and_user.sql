INSERT INTO public.adm_organization (organization_id, org_name, org_description)
VALUES (2500, 'FedEx', null);

INSERT INTO public.adm_user (user_id, organization_id, full_name, email, password, address, unique_identification_code)
VALUES (2500, 2500, 'Usuario root', 'root@shipping.dev', '$2a$10$sXe9HqBwA1U3l/3HKUlYHetw83heXcLeP4UbKgIzaHuzvWiAt4uxO', null, '1234567891234');
