CREATE TABLE orderitem (
    oid        VARCHAR2(20) NOT NULL,
    pid        VARCHAR2(30) NOT NULL,
    ccolorcode VARCHAR2(10) NOT NULL,
    ssize      VARCHAR2(10) NOT NULL,
    oamount    NUMBER NOT NULL
);

ALTER TABLE orderitem
    ADD CONSTRAINT orderitem_pk PRIMARY KEY ( pid,
                                              ccolorcode,
                                              ssize,
                                              oid );

CREATE TABLE orderlist (
    oid              VARCHAR2(20) NOT NULL,
    mid              VARCHAR2(20) NOT NULL,
    ozipcode         NUMBER NOT NULL,
    oaddress1        VARCHAR2(200) NOT NULL,
    oaddress2        VARCHAR2(200),
    odate            DATE NOT NULL,
    oreceiver        VARCHAR2(50) NOT NULL,
    otel             VARCHAR2(20) NOT NULL,
    ousedmileage     NUMBER,
    ousedcoupon      NUMBER,
    opayment         NUMBER NOT NULL,
    ostatus          NUMBER NOT NULL,
    oaccountdeadline DATE,
    odiscounted      NUMBER
);

ALTER TABLE orderlist ADD CONSTRAINT orderlist_pk PRIMARY KEY ( oid );

ALTER TABLE orderitem
    ADD CONSTRAINT orderitem_orderlist_fk FOREIGN KEY ( oid )
        REFERENCES orderlist ( oid )
            ON DELETE CASCADE;