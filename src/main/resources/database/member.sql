CREATE TABLE coupon (
    ccode      VARCHAR2(100) NOT NULL,
    eid        NUMBER NOT NULL,
    mid        VARCHAR2(20) NOT NULL,
    cname      VARCHAR2(20) NOT NULL,
    cstartdate DATE,
    cenddate   DATE,
    cstate     NUMBER NOT NULL
);

ALTER TABLE coupon ADD CONSTRAINT coupon_pk PRIMARY KEY ( ccode );

CREATE TABLE event (
    eid        NUMBER NOT NULL,
    ename      VARCHAR2(100) NOT NULL,
    edetail    VARCHAR2(1000),
    estartdate TIMESTAMP NOT NULL,
    eenddate   TIMESTAMP NOT NULL,
    eimage     VARCHAR2(500) NOT NULL,
    eamount    NUMBER NOT NULL,
    elimit     NUMBER NOT NULL
);

ALTER TABLE event ADD CONSTRAINT event_pk PRIMARY KEY ( eid );

CREATE TABLE member (
    mid       VARCHAR2(20) NOT NULL,
    mpassword VARCHAR2(20) NOT NULL,
    mname     VARCHAR2(50) NOT NULL,
    memail    VARCHAR2(50) NOT NULL,
    mtel      VARCHAR2(20) NOT NULL,
    mzipcode  NUMBER,
    maddress1 VARCHAR2(200),
    maddress2 VARCHAR2(200),
    mgrade    VARCHAR2(30),
    mdate     DATE NOT NULL,
    mpoint    NUMBER NOT NULL,
    menabled  NUMBER NOT NULL
);

ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY ( mid );

CREATE TABLE mycart (
    mid     VARCHAR2(20) NOT NULL,
    pid     VARCHAR2(30) NOT NULL,
    psize   VARCHAR2(10) NOT NULL,
    pcolor  VARCHAR2(10) NOT NULL,
    pamount NUMBER NOT NULL
);

ALTER TABLE mycart
    ADD CONSTRAINT mycart_pk PRIMARY KEY ( mid,
                                           pid,
                                           psize,
                                           pcolor );

CREATE TABLE qna (
    qid           NUMBER NOT NULL,
    mid           VARCHAR2(20) NOT NULL,
    qtitle        VARCHAR2(50) NOT NULL,
    qcontent      VARCHAR2(4000) NOT NULL,
    qdate         DATE NOT NULL,
    qreplydate    DATE,
    qreplytitle   VARCHAR2(50),
    qreplycontent VARCHAR2(4000),
    qmanager      VARCHAR2(25)
);

ALTER TABLE qna ADD CONSTRAINT qna_pk PRIMARY KEY ( qid );

CREATE TABLE wishlist (
    mid VARCHAR2(20) NOT NULL,
    pid VARCHAR2(30) NOT NULL
);

ALTER TABLE wishlist ADD CONSTRAINT wishlist_pk PRIMARY KEY ( pid,
                                                              mid );

ALTER TABLE coupon
    ADD CONSTRAINT coupon_event_fk FOREIGN KEY ( eid )
        REFERENCES event ( eid );

ALTER TABLE coupon
    ADD CONSTRAINT coupon_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid )
            ON DELETE CASCADE;

ALTER TABLE mycart
    ADD CONSTRAINT mycart_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid )
            ON DELETE CASCADE;

ALTER TABLE qna
    ADD CONSTRAINT qna_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid );

ALTER TABLE wishlist
    ADD CONSTRAINT wishlist_member_fk FOREIGN KEY ( mid )
        REFERENCES member ( mid )
            ON DELETE CASCADE;