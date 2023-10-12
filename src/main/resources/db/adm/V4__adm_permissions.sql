ALTER TABLE public.adm_permission ALTER COLUMN icon DROP NOT NULL;
ALTER TABLE public.adm_permission ALTER COLUMN parent_permission_id DROP NOT NULL;
ALTER TABLE public.adm_permission ADD internal_id INTEGER DEFAULT 0::INTEGER NOT NULL;

CREATE INDEX adm_permission_index ON public.adm_permission USING BTREE(internal_id);

INSERT INTO public.adm_permission (permission_id, internal_id, parent_permission_id, name, sref, icon, priority) VALUES
(5100, 100, null, 'global', '', null, 1),
(5101, 101, 5100, 'administration', '/administration', 'admin_panel_settings', 1),
(5102, 102, 5101, 'roles', '/administration/roles', 'shield_person', 1),
(5103, 103, 5101, 'users', '/administration/users', 'person', 2);
