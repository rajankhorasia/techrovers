--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 10.10

-- Started on 2021-07-22 09:40:27

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 201 (class 1259 OID 33613)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 200 (class 1259 OID 33593)
-- Name: invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice (
    id bigint NOT NULL,
    invoice_number character varying(45) NOT NULL,
    date date NOT NULL,
    gst_amount numeric(10,2),
    user_id bigint NOT NULL
);


ALTER TABLE public.invoice OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 33588)
-- Name: invoice_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invoice_details (
    id bigint NOT NULL,
    invoice_id bigint NOT NULL,
    item_id bigint NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.invoice_details OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 33583)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id bigint NOT NULL,
    name character varying(45) NOT NULL,
    item_desc character varying(200) NOT NULL,
    gst integer NOT NULL,
    total_price numeric(10,2) NOT NULL
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 33568)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    role_name character varying(20) NOT NULL,
    type integer NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 33573)
-- Name: user_mst; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_mst (
    id bigint NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    username character varying(100) NOT NULL,
    email character varying(45) NOT NULL,
    phone character varying(15) NOT NULL,
    createddate date NOT NULL,
    updateddate date,
    roleid bigint NOT NULL
);


ALTER TABLE public.user_mst OWNER TO postgres;


--
-- TOC entry 2821 (class 0 OID 33568)
-- Dependencies: 196
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, role_name, type) FROM stdin;
1	ADMIN_ROLE	1
2	USER_ROLE	2
\.


--
-- TOC entry 2693 (class 2606 OID 33592)
-- Name: invoice_details invoice_details_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details
    ADD CONSTRAINT invoice_details_pk PRIMARY KEY (id);


--
-- TOC entry 2695 (class 2606 OID 33597)
-- Name: invoice invoice_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY (id);


--
-- TOC entry 2691 (class 2606 OID 33587)
-- Name: item item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pk PRIMARY KEY (id);


--
-- TOC entry 2687 (class 2606 OID 33572)
-- Name: role role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);


--
-- TOC entry 2689 (class 2606 OID 33577)
-- Name: user_mst user_mst_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_mst
    ADD CONSTRAINT user_mst_pk PRIMARY KEY (id);


--
-- TOC entry 2697 (class 2606 OID 33598)
-- Name: invoice_details invoice_details_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details
    ADD CONSTRAINT invoice_details_fk FOREIGN KEY (invoice_id) REFERENCES public.invoice(id);


--
-- TOC entry 2698 (class 2606 OID 33603)
-- Name: invoice_details invoice_details_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice_details
    ADD CONSTRAINT invoice_details_fk_1 FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- TOC entry 2699 (class 2606 OID 33608)
-- Name: invoice invoice_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_fk FOREIGN KEY (user_id) REFERENCES public.user_mst(id);


--
-- TOC entry 2696 (class 2606 OID 33578)
-- Name: user_mst user_mst_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_mst
    ADD CONSTRAINT user_mst_fk FOREIGN KEY (roleid) REFERENCES public.role(id);


-- Completed on 2021-07-22 09:40:27

--
-- PostgreSQL database dump complete
--

