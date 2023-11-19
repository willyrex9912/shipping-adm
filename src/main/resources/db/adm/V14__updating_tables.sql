CREATE TABLE public.adm_parameter
(
    parameter_id          BIGINT       NOT NULL,
    value                 VARCHAR(750) NOT NULL,
    description           VARCHAR(300) NOT NULL,
    category_parameter_id BIGINT       NOT NULL,
    CONSTRAINT adm_parameter_pk PRIMARY KEY (parameter_id)
);

ALTER TABLE adm_vehicle
    RENAME COLUMN category_status TO category_status_id;

ALTER TABLE public.adm_parameter
    ADD CONSTRAINT adm_parameter_adm_category_fk
        FOREIGN KEY (category_parameter_id)
            REFERENCES public.adm_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE public.adm_vehicle
    ADD CONSTRAINT adm_vehicle_adm_category_fk
        FOREIGN KEY (category_status_id)
            REFERENCES public.adm_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE SEQUENCE SEQ_ADM_VEHICLE START WITH 5000 INCREMENT BY 1;
CREATE SEQUENCE SEQ_ADM_PARAMETER START WITH 5000 INCREMENT BY 1;