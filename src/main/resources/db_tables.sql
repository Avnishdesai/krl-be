
CREATE TABLE krl.member
(
    id integer NOT NULL DEFAULT nextval('krl.uzer_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT uzer_pkey PRIMARY KEY (id)
)

CREATE TABLE krl.round
(
    id integer NOT NULL DEFAULT nextval('krl.round_id_seq'::regclass),
    seq numeric NOT NULL,
    start_date date NOT NULL,
    end_date date,
    CONSTRAINT round_pkey PRIMARY KEY (id)
)

CREATE TABLE krl.team
(
    id integer NOT NULL DEFAULT nextval('krl.team_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    round_id integer NOT NULL,
    CONSTRAINT team_pkey PRIMARY KEY (id),
    CONSTRAINT round_fk FOREIGN KEY (round_id)
        REFERENCES krl.round (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE krl.team_member
(
    id integer NOT NULL DEFAULT nextval('krl.team_members_id_seq'::regclass),
    round_id integer NOT NULL,
    member_id integer NOT NULL,
    team_id integer NOT NULL,
    CONSTRAINT team_member_pkey PRIMARY KEY (id),
    CONSTRAINT member_fk FOREIGN KEY (member_id)
        REFERENCES krl.member (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT round_fk FOREIGN KEY (round_id)
        REFERENCES krl.round (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT team_fk FOREIGN KEY (team_id)
        REFERENCES krl.team (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE krl.question
(
    id integer NOT NULL DEFAULT nextval('krl.question_id_seq'::regclass),
    description text COLLATE pg_catalog."default" NOT NULL,
    round_id integer NOT NULL,
    points numeric NOT NULL,
    CONSTRAINT question_pkey PRIMARY KEY (id),
    CONSTRAINT question_round_fk FOREIGN KEY (round_id)
        REFERENCES krl.round (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE krl.response
(
    id integer NOT NULL DEFAULT nextval('krl.response_id_seq'::regclass),
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    applicable_date date NOT NULL,
    question_id integer NOT NULL,
    value text COLLATE pg_catalog."default",
    member_id integer,
    CONSTRAINT response_pkey PRIMARY KEY (id),
    CONSTRAINT member_fk FOREIGN KEY (member_id)
        REFERENCES krl.member (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT question_fk FOREIGN KEY (question_id)
        REFERENCES krl.question (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)