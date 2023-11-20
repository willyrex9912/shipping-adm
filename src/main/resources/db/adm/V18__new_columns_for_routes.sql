ALTER TABLE public.adm_org_route_step ADD COLUMN average_time NUMERIC(5, 2) DEFAULT 0::NUMERIC NOT NULL;
ALTER TABLE public.adm_org_route_step ADD COLUMN average_cost_per_km NUMERIC(20, 6) DEFAULT 0::NUMERIC NOT NULL;
ALTER TABLE public.adm_org_route_step ADD COLUMN number_of_trips BIGINT DEFAULT 0::BIGINT NOT NULL;

ALTER TABLE public.adm_package_route ADD COLUMN estimated_distance NUMERIC(7, 2) DEFAULT 0::NUMERIC NOT NULL;
