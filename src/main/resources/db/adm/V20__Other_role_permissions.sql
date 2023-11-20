-- role-permissions
INSERT INTO adm_role_permission (role_permission_id, role_id, permission_id, create_permission, read_permission, update_permission, delete_permission) VALUES
-- root
(2514, 2500, 5106, true, true, true, true),
(2515, 2500, 5107, true, true, true, true),
(2516, 2500, 5108, true, true, true, true),
(2517, 2500, 5109, true, true, true, true),

-- root branch 1
(2518, 2501, 5106, true, true, true, true),
(2519, 2501, 5107, true, true, true, true),
(2520, 2501, 5108, true, true, true, true),
(2521, 2501, 5109, true, true, true, true),

-- root branch 2
(2522, 2502, 5106, true, true, true, true),
(2523, 2502, 5107, true, true, true, true),
(2524, 2502, 5108, true, true, true, true),
(2525, 2502, 5109, true, true, true, true);