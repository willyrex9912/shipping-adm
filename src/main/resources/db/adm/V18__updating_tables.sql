UPDATE adm_category SET internal_id = 518 WHERE category_id = 10517;
UPDATE adm_category SET internal_id = 519 WHERE category_id = 10518;
UPDATE adm_category SET internal_id = 520 WHERE category_id = 10519;
UPDATE adm_category SET internal_id = 521 WHERE category_id = 10520;
UPDATE adm_category SET internal_id = 522 WHERE category_id = 10521;

INSERT INTO adm_category (category_id, parent_category_id, internal_id, description)
VALUES
    (10540, NULL, 540, 'Parameter_Category'),
    (10541, 10540, 541, 'PC_Priority_Parameter'),
    (10542, 10541, 542, 'PP_Time'),
    (10543, 10541, 543, 'PP_Cost'),
    (10550, 10540, 550, 'PC_Behavior_Parameter'),
    (10551, 10550, 551, 'BP_Vehicle_Return'),
    (10552, 10550, 552, 'BP_Recalculate_By_Node'),
    (10560, 10540, 560, 'PC_Configuration_Parameter'),
    (10561, 10560, 561, 'CP_Maximun_Waiting_Time'),
    (10562, 10560, 562, 'CP_Minimum_Capacity'),
    (10563, 10560, 563, 'CP_Storage_Cost'),
    (10570, NULL, 560, 'Vehicle_Category'),
    (10571, 10570, 571, 'VC_Small'),
    (10572, 10570, 572, 'VC_Medium'),
    (10573, 10570, 573, 'VC_Large'),
    (10574, 10570, 574, 'VC_Other')
;

ALTER TABLE adm_vehicle
    DROP COLUMN category_status_id;

ALTER TABLE adm_vehicle
    ADD COLUMN avg_speed numeric(10, 2) DEFAULT 0::NUMERIC NOT NULL;

ALTER TABLE adm_vehicle
    ADD COLUMN vehicle_category_id BIGINT NOT NULL;

ALTER TABLE adm_vehicle
    ADD COLUMN status_category_id BIGINT NOT NULL;

ALTER TABLE public.adm_vehicle
    ADD CONSTRAINT adm_vehicle_adm_category_fk
        FOREIGN KEY (vehicle_category_id)
            REFERENCES public.adm_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE public.adm_vehicle
    ADD CONSTRAINT adm_vehicle_adm_category_fk2
        FOREIGN KEY (status_category_id)
            REFERENCES public.adm_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE public.adm_vehicle_type
(
    vehicle_type_id     BIGINT         NOT NULL,
    description         VARCHAR(100)   NOT NULL,
    weight_fee          NUMERIC(5, 2)  NOT NULL,
    distance_fee        NUMERIC(5, 2)  NOT NULL,
    vehicle_category_id BIGINT         NOT NULL,
    CONSTRAINT adm_vehicle_type_pk PRIMARY KEY (vehicle_type_id)
);

CREATE SEQUENCE SEQ_ADM_VEHICLE_TYPE START WITH 5000 INCREMENT BY 1;

ALTER TABLE public.adm_vehicle_type
    ADD CONSTRAINT adm_vehicle_type_adm_category_fk
        FOREIGN KEY (vehicle_category_id)
            REFERENCES public.adm_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE adm_parameter
    DROP COLUMN category_parameter_id;

ALTER TABLE adm_parameter
    ADD COLUMN parameter_category_id BIGINT NOT NULL;

ALTER TABLE adm_parameter
    ADD COLUMN organization_id BIGINT NOT NULL;

ALTER TABLE public.adm_parameter
    ADD CONSTRAINT adm_parameter_adm_category_fk
        FOREIGN KEY (parameter_category_id)
            REFERENCES public.adm_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE public.adm_parameter
    ADD CONSTRAINT adm_parameter_adm_organization_fk
        FOREIGN KEY (organization_id)
            REFERENCES public.adm_organization (organization_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

INSERT INTO adm_parameter (parameter_id, organization_id, parameter_category_id, description, value)
VALUES
    (2500, 2500, 10542, 'Prioridad del menor tiempo', '1'),
    (2501, 2500, 10543, 'Prioridad del menor costo', '2'),
    (2502, 2500, 10551, 'Tomar en cuenta los retornos de los vehiculos', '1'),
    (2503, 2500, 10552, 'Recalcular ruta en cada nodo', '1'),
    (2504, 2500, 10561, 'Tiempo maximo de espera en semanas', '4'),
    (2505, 2500, 10562, 'Capacidad minima para que salga un camion', '0.5'),
    (2506, 2500, 10563, 'Costo de almacenaje por Q/kg/dia', '0.25'),

    (2507, 2501, 10542, 'Prioridad del menor tiempo', '1'),
    (2508, 2501, 10543, 'Prioridad del menor costo', '2'),
    (2509, 2501, 10551, 'Tomar en cuenta los retornos de los vehiculos', '1'),
    (2510, 2501, 10552, 'Recalcular ruta en cada nodo', '1'),
    (2511, 2501, 10561, 'Tiempo maximo de espera en semanas', '4'),
    (2512, 2501, 10562, 'Capacidad minima para que salga un camion', '0.5'),
    (2513, 2501, 10563, 'Costo de almacenaje por Q/kg/dia', '0.25'),

    (2514, 2502, 10542, 'Prioridad del menor tiempo', '1'),
    (2515, 2502, 10543, 'Prioridad del menor costo', '2'),
    (2516, 2502, 10551, 'Tomar en cuenta los retornos de los vehiculos', '1'),
    (2517, 2502, 10552, 'Recalcular ruta en cada nodo', '1'),
    (2518, 2502, 10561, 'Tiempo maximo de espera en semanas', '4'),
    (2519, 2502, 10562, 'Capacidad minima para que salga un camion', '0.5'),
    (2520, 2502, 10563, 'Costo de almacenaje por Q/kg/dia', '0.25')
;

INSERT INTO adm_vehicle (vehicle_id, organization_id, capacity, avg_cost_per_km, avg_speed, vehicle_category_id, status_category_id)
VALUES
    (2500, 2500, 160, 0.5, 70, 10571, 10518),
    (2501, 2501, 200, 0.6, 60, 10572, 10518),
    (2502, 2502, 300, 0.6, 50, 10573, 10518)
;

INSERT INTO adm_vehicle_type (vehicle_type_id, description, weight_fee, distance_fee, vehicle_category_id)
VALUES
    (2500, 'Vehiculo pequeño', 0.25, 0.1, 10571),
    (2501, 'Vehiculo mediano', 0.5, 0.2, 10572),
    (2502, 'Vehiculo grande', 0.75, 0.4, 10573)
;

UPDATE adm_organization SET parent_organization_id = 2500 WHERE parent_organization_id = 0;
UPDATE adm_user SET sub_organization_id = 2500 WHERE sub_organization_id = 0;
UPDATE adm_role SET sub_organization_id = 2500 WHERE sub_organization_id = 0;
UPDATE adm_operation_cost SET sub_organization_id = 2500 WHERE sub_organization_id = 0;





