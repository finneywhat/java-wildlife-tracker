--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: CnC
--

CREATE TABLE animals (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE animals OWNER TO "CnC";

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: CnC
--

CREATE SEQUENCE animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_id_seq OWNER TO "CnC";

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CnC
--

ALTER SEQUENCE animals_id_seq OWNED BY animals.id;


--
-- Name: endangered_animals; Type: TABLE; Schema: public; Owner: CnC
--

CREATE TABLE endangered_animals (
    id integer NOT NULL,
    name character varying,
    health character varying,
    age character varying
);


ALTER TABLE endangered_animals OWNER TO "CnC";

--
-- Name: endangered_animals_id_seq; Type: SEQUENCE; Schema: public; Owner: CnC
--

CREATE SEQUENCE endangered_animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE endangered_animals_id_seq OWNER TO "CnC";

--
-- Name: endangered_animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CnC
--

ALTER SEQUENCE endangered_animals_id_seq OWNED BY endangered_animals.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: CnC
--

CREATE TABLE sightings (
    id integer NOT NULL,
    animal_id integer,
    location character varying,
    ranger_name character varying,
    endangered_animal_id integer,
    "time" timestamp without time zone
);


ALTER TABLE sightings OWNER TO "CnC";

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: CnC
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO "CnC";

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CnC
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY animals ALTER COLUMN id SET DEFAULT nextval('animals_id_seq'::regclass);


--
-- Name: endangered_animals id; Type: DEFAULT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY endangered_animals ALTER COLUMN id SET DEFAULT nextval('endangered_animals_id_seq'::regclass);


--
-- Name: sightings id; Type: DEFAULT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: CnC
--

COPY animals (id, name) FROM stdin;
3	Deer
4	black bear
5	Heron
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CnC
--

SELECT pg_catalog.setval('animals_id_seq', 5, true);


--
-- Data for Name: endangered_animals; Type: TABLE DATA; Schema: public; Owner: CnC
--

COPY endangered_animals (id, name, health, age) FROM stdin;
3	Flying Donkey	Ill	Newborn
4	Unicorn of Life	Ill	Newborn
\.


--
-- Name: endangered_animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CnC
--

SELECT pg_catalog.setval('endangered_animals_id_seq', 4, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: CnC
--

COPY sightings (id, animal_id, location, ranger_name, endangered_animal_id, "time") FROM stdin;
10	10	Zone 5	Doug Less	\N	2017-04-07 14:43:25.737456
6	3	Zone 7	Clint Wellworn	\N	\N
7	0	Zone 7	Cherry Sunborn	\N	\N
8	3			\N	\N
9	0			\N	\N
11	3	here	c	\N	\N
12	0	Somewhere	Cherry Sunborn	\N	\N
13	0	asdhf	adsf	\N	\N
14	0	gh	gh	\N	\N
15	0	Please Work	Chris Finney	3	\N
16	0	Work again	Cherry Sunborn	4	\N
17	3	WORK!	Please work!!	0	\N
18	4	Everywhere	Chris Finney	0	\N
19	4	My backyard	Me	0	\N
20	0	here	Cherry Sunborn	3	\N
21	5	Zone 4	Chris Finney	0	\N
22	0	Somewhere	Joe Budden	4	\N
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CnC
--

SELECT pg_catalog.setval('sightings_id_seq', 22, true);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: endangered_animals endangered_animals_pkey; Type: CONSTRAINT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY endangered_animals
    ADD CONSTRAINT endangered_animals_pkey PRIMARY KEY (id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

