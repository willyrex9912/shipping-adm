INSERT INTO public.adm_permission (permission_id, internal_id, parent_permission_id, name, sref, icon, priority) VALUES
    (5104, 104, 5101, 'vehicles', '/administration/vehicles', 'directions_car', 3),
    (5105, 105, 5101, 'parameters', '/administration/parameters', 'settings', 4)
;

INSERT INTO adm_role_permission (role_permission_id, role_id, permission_id, create_permission, read_permission, update_permission, delete_permission) VALUES
(2512, 2500, 5104, true, true, true, true),
(2513, 2500, 5105, true, true, true, true)
;