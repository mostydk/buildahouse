-- Table: public.project_state

-- DROP TABLE IF EXISTS public.project_state;

CREATE TABLE IF NOT EXISTS public.project_state
(
    id integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT project_state_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.project_state
    OWNER to buildahouse;
-- Index: uix_project_state_name

-- DROP INDEX IF EXISTS public.uix_project_state_name;

CREATE UNIQUE INDEX IF NOT EXISTS uix_project_state_name
    ON public.project_state USING btree
    (name COLLATE pg_catalog."default" ASC NULLS LAST)
    NULLS NOT DISTINCT
    WITH (fillfactor=100, deduplicate_items=True)
    TABLESPACE pg_default;
    
-- Table: public.project

-- DROP TABLE IF EXISTS public.project;

CREATE TABLE IF NOT EXISTS public.project
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    state_id integer NOT NULL,
    CONSTRAINT project_pkey PRIMARY KEY (id),
    CONSTRAINT fk_project_state_id FOREIGN KEY (state_id)
        REFERENCES public.project_state (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.project
    OWNER to buildahouse;
-- Index: fki_fk_project_state_id

-- DROP INDEX IF EXISTS public.fki_fk_project_state_id;

CREATE INDEX IF NOT EXISTS fki_fk_project_state_id
    ON public.project USING btree
    (state_id ASC NULLS LAST)
    WITH (fillfactor=100, deduplicate_items=True)
    TABLESPACE pg_default;
-- Index: uix_project_name

-- DROP INDEX IF EXISTS public.uix_project_name;

CREATE UNIQUE INDEX IF NOT EXISTS uix_project_name
    ON public.project USING btree
    (name COLLATE pg_catalog."default" ASC NULLS LAST)
    NULLS NOT DISTINCT
    WITH (fillfactor=100, deduplicate_items=True)
    TABLESPACE pg_default;