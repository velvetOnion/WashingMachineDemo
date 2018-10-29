DROP TABLE IF EXISTS MODE;
DROP TABLE IF EXISTS WASHING_MACHINES;


/* ----------------------------------------------------------------- */
/* Table : MODE */
/* ----------------------------------------------------------------- */

CREATE TABLE MODE (
	ID 				   BIGINT           not null,
	WM_ID 		   BIGINT           not null,
	NAME 				 VARCHAR(150)     not null,
	TEMPERATURE  INT              not null,
	PRIMARY KEY (ID)
);


/* ----------------------------------------------------------------- */
/* Table : WASHING_MACHINES */
/*----------------------------------------------------------------- */
CREATE TABLE WASHING_MACHINES (
	ID      BIGINT          not null,
	NAME    VARCHAR(150)    not null,
	STATE   VARCHAR(150)    not null,
	PRIMARY KEY (ID)
);