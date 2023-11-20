INSERT INTO public.adm_permission (permission_id, internal_id, parent_permission_id, name, sref, icon, priority)
VALUES (5106, 106, 5101, 'organizations', '/administration/organizations', '', 2),
       (5107, 107, 5101, 'operation-costs', '/administration/operation-costs', '', 2),
       (5108, 108, 5100, 'delivery', '/delivery', '', 1),
       (5109, 109, 5108, 'packages', '/delivery/packages', '', 1)
;