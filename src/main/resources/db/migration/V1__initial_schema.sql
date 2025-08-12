--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5 (Debian 17.5-1.pgdg120+1)
-- Dumped by pg_dump version 17.5 (Debian 17.5-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: _user; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public._user (
    created_at timestamp(6) with time zone NOT NULL,
    last_login timestamp(6) with time zone,
    id uuid NOT NULL,
    clinic character varying(255),
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    profile_image_url character varying(255),
    role character varying(255),
    tenant_id character varying(255) NOT NULL,
    CONSTRAINT _user_role_check CHECK (((role)::text = ANY ((ARRAY['ROLE_DOCTOR'::character varying, 'ROLE_INDIVIDUAL'::character varying, 'ROLE_ADMIN'::character varying])::text[])))
);


ALTER TABLE public._user OWNER TO dimitris;

--
-- Name: ckd_epi_calculation; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.ckd_epi_calculation (
    age integer NOT NULL,
    creatinine double precision NOT NULL,
    result double precision NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    id uuid NOT NULL,
    patient_id uuid NOT NULL,
    user_id uuid NOT NULL,
    notes text,
    sex character varying(255),
    CONSTRAINT ckd_epi_calculation_sex_check CHECK (((sex)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying])::text[])))
);


ALTER TABLE public.ckd_epi_calculation OWNER TO dimitris;

--
-- Name: disease; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.disease (
    has_disease boolean NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    id uuid NOT NULL,
    patient_id uuid NOT NULL,
    details text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.disease OWNER TO dimitris;

--
-- Name: kfre_calculation; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.kfre_calculation (
    acr double precision NOT NULL,
    age integer NOT NULL,
    egfr double precision NOT NULL,
    risk2yr double precision NOT NULL,
    risk5yr double precision NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    id uuid NOT NULL,
    patient_id uuid NOT NULL,
    user_id uuid NOT NULL,
    notes text,
    sex character varying(255) NOT NULL,
    CONSTRAINT kfre_calculation_sex_check CHECK (((sex)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying])::text[])))
);


ALTER TABLE public.kfre_calculation OWNER TO dimitris;

--
-- Name: medication; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.medication (
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    added_by_user_id uuid,
    id uuid NOT NULL,
    dosage character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.medication OWNER TO dimitris;

--
-- Name: medication_assignment; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.medication_assignment (
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    disease_id uuid NOT NULL,
    id uuid NOT NULL,
    medication_id uuid NOT NULL,
    patient_id uuid NOT NULL,
    frequency character varying(255)
);


ALTER TABLE public.medication_assignment OWNER TO dimitris;

--
-- Name: patient; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.patient (
    active boolean NOT NULL,
    birth_date date NOT NULL,
    last_egfr_result double precision NOT NULL,
    last_risk2yr double precision NOT NULL,
    last_risk5yr double precision NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    last_assessment_date timestamp(6) with time zone,
    updated_at timestamp(6) with time zone NOT NULL,
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    first_name character varying(255) NOT NULL,
    gender character varying(255) NOT NULL,
    general_history_note text,
    last_name character varying(255) NOT NULL,
    last_risk character varying(255),
    CONSTRAINT patient_gender_check CHECK (((gender)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying])::text[]))),
    CONSTRAINT patient_last_risk_check CHECK (((last_risk)::text = ANY ((ARRAY['HIGH'::character varying, 'MEDIUM'::character varying, 'LOW'::character varying, 'UNKNOWN'::character varying])::text[])))
);


ALTER TABLE public.patient OWNER TO dimitris;

--
-- Name: report; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.report (
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    id uuid NOT NULL,
    patient_id uuid NOT NULL,
    user_id uuid NOT NULL,
    file_url character varying(255)
);


ALTER TABLE public.report OWNER TO dimitris;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: dimitris
--

CREATE TABLE public.settings (
    autosave boolean NOT NULL,
    dark_mode boolean NOT NULL,
    notifications boolean NOT NULL,
    created_at timestamp(6) with time zone NOT NULL,
    updated_at timestamp(6) with time zone NOT NULL,
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    export_format character varying(255)
);


ALTER TABLE public.settings OWNER TO dimitris;

--
-- Name: _user _user_email_key; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public._user
    ADD CONSTRAINT _user_email_key UNIQUE (email);


--
-- Name: _user _user_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public._user
    ADD CONSTRAINT _user_pkey PRIMARY KEY (id);


--
-- Name: ckd_epi_calculation ckd_epi_calculation_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.ckd_epi_calculation
    ADD CONSTRAINT ckd_epi_calculation_pkey PRIMARY KEY (id);


--
-- Name: disease disease_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.disease
    ADD CONSTRAINT disease_pkey PRIMARY KEY (id);


--
-- Name: kfre_calculation kfre_calculation_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.kfre_calculation
    ADD CONSTRAINT kfre_calculation_pkey PRIMARY KEY (id);


--
-- Name: medication_assignment medication_assignment_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication_assignment
    ADD CONSTRAINT medication_assignment_pkey PRIMARY KEY (id);


--
-- Name: medication medication_name_key; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT medication_name_key UNIQUE (name);


--
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);


--
-- Name: patient patient_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- Name: report report_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (id);


--
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- Name: settings settings_user_id_key; Type: CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_user_id_key UNIQUE (user_id);


--
-- Name: medication fk3d6tnmyc5d5tkg0bm8jqsy3wm; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT fk3d6tnmyc5d5tkg0bm8jqsy3wm FOREIGN KEY (added_by_user_id) REFERENCES public._user(id);


--
-- Name: report fk3vkiw8dsqjansy094gwob6ckd; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT fk3vkiw8dsqjansy094gwob6ckd FOREIGN KEY (patient_id) REFERENCES public.patient(id);


--
-- Name: medication_assignment fk5xsc3jdbkymk8cdch4dtakwxf; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication_assignment
    ADD CONSTRAINT fk5xsc3jdbkymk8cdch4dtakwxf FOREIGN KEY (medication_id) REFERENCES public.medication(id);


--
-- Name: settings fkamv80bchf6ru1lhotn394box7; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT fkamv80bchf6ru1lhotn394box7 FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- Name: medication_assignment fkappxpeiv4w67mf3ywj9010cxb; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication_assignment
    ADD CONSTRAINT fkappxpeiv4w67mf3ywj9010cxb FOREIGN KEY (patient_id) REFERENCES public.patient(id);


--
-- Name: disease fkc579od6jyxfs2aivl5im1diga; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.disease
    ADD CONSTRAINT fkc579od6jyxfs2aivl5im1diga FOREIGN KEY (patient_id) REFERENCES public.patient(id);


--
-- Name: medication_assignment fkd9x5khivpm2xqspwvu9gn8a1v; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.medication_assignment
    ADD CONSTRAINT fkd9x5khivpm2xqspwvu9gn8a1v FOREIGN KEY (disease_id) REFERENCES public.disease(id);


--
-- Name: kfre_calculation fkeexyuky193202aswq5ua5rvwu; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.kfre_calculation
    ADD CONSTRAINT fkeexyuky193202aswq5ua5rvwu FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- Name: ckd_epi_calculation fkgbcauhsnl7bmj43r6ngc70iwm; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.ckd_epi_calculation
    ADD CONSTRAINT fkgbcauhsnl7bmj43r6ngc70iwm FOREIGN KEY (patient_id) REFERENCES public.patient(id);


--
-- Name: kfre_calculation fkm13ph6ojjpjxls199gq2yen4y; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.kfre_calculation
    ADD CONSTRAINT fkm13ph6ojjpjxls199gq2yen4y FOREIGN KEY (patient_id) REFERENCES public.patient(id);


--
-- Name: ckd_epi_calculation fkoles6oky6q5f18a7q9qkj6sqw; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.ckd_epi_calculation
    ADD CONSTRAINT fkoles6oky6q5f18a7q9qkj6sqw FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- Name: report fkp3sln2k2k3hab1pn9ubqy2qu6; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT fkp3sln2k2k3hab1pn9ubqy2qu6 FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- Name: patient fkt5uxr9ai55upmin638pjn1jdv; Type: FK CONSTRAINT; Schema: public; Owner: dimitris
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT fkt5uxr9ai55upmin638pjn1jdv FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- PostgreSQL database dump complete
--

