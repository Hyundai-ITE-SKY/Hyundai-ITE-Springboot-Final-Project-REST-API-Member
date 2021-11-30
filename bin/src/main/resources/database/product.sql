CREATE TABLE brand (
    bno   NUMBER NOT NULL,
    bname VARCHAR2(50) NOT NULL
);

ALTER TABLE brand ADD CONSTRAINT brand_pk PRIMARY KEY ( bno );

CREATE TABLE category (
    clarge  VARCHAR2(50) NOT NULL,
    cmedium VARCHAR2(50) NOT NULL,
    csmall  VARCHAR2(50) NOT NULL
);

ALTER TABLE category
    ADD CONSTRAINT category_pk PRIMARY KEY ( clarge,
                                             cmedium,
                                             csmall );

CREATE TABLE color (
    pid         VARCHAR2(30) NOT NULL,
    ccolorcode  VARCHAR2(10) NOT NULL,
    cimage1     VARCHAR2(500) NOT NULL,
    cimage2     VARCHAR2(500),
    cimage3     VARCHAR2(500),
    ccolorimage VARCHAR2(500) NOT NULL,
    cmatchpid   VARCHAR2(30)
);

ALTER TABLE color ADD CONSTRAINT color_pk PRIMARY KEY ( ccolorcode,
                                                        pid );

CREATE TABLE product (
    pid          VARCHAR2(30) NOT NULL,
    bno          NUMBER NOT NULL,
    clarge       VARCHAR2(50) NOT NULL,
    cmedium      VARCHAR2(50) NOT NULL,
    csmall       VARCHAR2(50) NOT NULL,
    pno          NUMBER NOT NULL,
    pname        VARCHAR2(50) NOT NULL,
    pprice       NUMBER NOT NULL,
    pdetail      VARCHAR2(4000 BYTE) NOT NULL,
    pseason      VARCHAR2(10) NOT NULL,
    ptotalamount NUMBER NOT NULL
);

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( pid );

CREATE TABLE stock (
    pid        VARCHAR2(30) NOT NULL,
    ccolorcode VARCHAR2(10) NOT NULL,
    ssize      VARCHAR2(10) NOT NULL,
    samount    NUMBER NOT NULL
);

ALTER TABLE stock
    ADD CONSTRAINT stock_pk PRIMARY KEY ( ssize,
                                          ccolorcode,
                                          pid );

ALTER TABLE color
    ADD CONSTRAINT color_product_fk FOREIGN KEY ( pid )
        REFERENCES product ( pid )
            ON DELETE CASCADE;

ALTER TABLE product
    ADD CONSTRAINT product_brand_fk FOREIGN KEY ( bno )
        REFERENCES brand ( bno )
            ON DELETE CASCADE;

ALTER TABLE product
    ADD CONSTRAINT product_category_fk FOREIGN KEY ( clarge,
                                                     cmedium,
                                                     csmall )
        REFERENCES category ( clarge,
                              cmedium,
                              csmall )
            ON DELETE CASCADE;

ALTER TABLE stock
    ADD CONSTRAINT stock_color_fk FOREIGN KEY ( ccolorcode,
                                                pid )
        REFERENCES color ( ccolorcode,
                           pid )
            ON DELETE CASCADE;