# GestioneArticoliJspServletJdbc

#DataBase (MySQL)
## Tabelle


Articolo:
-----------------------------------------------------
ID [INT AI PK]

CODICE [VARCHAR(45)]

DESCRIZIONE [VARCHAR(45)]

PREZZO [INT]

CATEGORIA_FK [INT]


Categoria:
-----------------------------------------------------
ID_CATEGORIA [INT AI PK]

DESCRIZIONE [VARCHAR(45)]


User:
-----------------------------------------------------
ID_USER [INT AI PK]

NOME [VARCHAR(45)]

COGNOME [VARCHAR(45)]

CODICE_FISCALE [CHAR(16)]

PASSWORD [VARCHAR(45)]

RUOLO [ENUM('ADMIN','OPERATORE','GUEST')]

##NOTA:
-----------------------------------------------------
Inserire i tre tipi di utenti manualmente da MySQL
