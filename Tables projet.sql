CREATE SEQUENCE team_sequence START 1 INCREMENT 1;
CREATE TABLE team (
    teamID  integer not null default nextval('team_sequence'),
    teamName varchar(50) not NULL,
    teamMoney integer default NULL,
    teamPets integer default NULL,

    CONSTRAINT pk_teamID primary key (teamID)
);

CREATE SEQUENCE player_sequence START 1 INCREMENT 1;
CREATE TABLE player (
    playerID  integer not null default nextval('player_sequence'),
    playerName varchar(50) not NULL,
    playerPassword varchar(50) not NULL,
    lastConnexion timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    teamID  integer not null default nextval('team_sequence'),
    
    constraint pk_playerID primary KEY (playerID)
);

CREATE SEQUENCE hero_sequence START 1 INCREMENT 1;
CREATE TABLE hero (
    playerID  integer not null default nextval('hero_sequence'),
    gold integer default NULL,
    damage integer not NULL default 1,
    pets integer default NULL,
    artefactMoney integer default NULL,
    artefacts varchar(150) default NULL, 
    
    CONSTRAINT pk_statsPlayerID primary key (playerID),
    CONSTRAINT fk_hero_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);

CREATE SEQUENCE monster_sequence START 1 INCREMENT 1;
CREATE TABLE monster (
    playerID integer not null default nextval('monster_sequence'),
    pv integer not NULL default 10,
    "add" integer not NULL default 10,
    "number" integer not NULL default 1,
    goldIncrease integer not NULL default 6,
    wave integer default NULL,

    CONSTRAINT pk_monsterPlayerID primary key (playerID),
    CONSTRAINT fk_monster_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);


CREATE SEQUENCE pets_sequence START 1 INCREMENT 1;
CREATE TABLE pets (
    playerID integer not null default nextval('pets_sequence'),
    damage integer not NULL default 1,
    "add" integer not NULL default 1,
    amount integer default NULL,
    buyCost integer not NULL default 100,
    upgradeCost integer not NULL default 150,

    CONSTRAINT pk_petsPlayerID primary key (playerID),
    CONSTRAINT fk_pets_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);

DROP TABLE pets,hero,team,player,monster
