CREATE TABLE artefact (
    artefactID SERIAL,
    artefactName varchar(50) not NULL,
    
    CONSTRAINT pk_artefactID primary key (artefactID)
);

CREATE TABLE team (
    teamID SERIAL,
    teamName varchar(50) not NULL,
    teamMoney integer default NULL,
    teamPets integer default NULL,

    CONSTRAINT pk_teamID primary key (teamID)
);

CREATE TABLE player (
    playerID SERIAL,
    playerName varchar(50) not NULL,
    playerPassword varchar(50) not NULL,
    lastConnexion timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    teamID SERIAL,
    
    constraint pk_playerID primary KEY (playerID),
    CONSTRAINT fk_player_team FOREIGN KEY (teamID) REFERENCES team(teamID)
);

CREATE TABLE hero (
    playerID SERIAL,
    gold integer default NULL,
    damage integer not NULL default 1,
    pets integer default NULL,
    artefactMoney integer default NULL,
    artefactID SERIAL, 
    
    CONSTRAINT pk_statsPlayerID primary key (playerID),
    CONSTRAINT fk_hero_player FOREIGN KEY (playerID) REFERENCES player(playerID),
    CONSTRAINT fk_hero_artefact FOREIGN KEY (artefactID) REFERENCES artefact(artefactID)
);

CREATE TABLE monster (
    playerID SERIAL,
    pv integer not NULL default 10,
    "add" integer not NULL default 10,
    "number" integer not NULL default 1,
    boss integer not NULL default 10,
    goldIncrease integer not NULL default 6,
    wave integer default NULL,

    CONSTRAINT pk_monsterPlayerID primary key (playerID),
    CONSTRAINT fk_monster_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);

CREATE TABLE pets (
    playerID SERIAL,
    damage integer not NULL default 1,
    "add" integer not NULL default 1,
    amount integer default NULL,
    buyCost integer not NULL default 100,
    upgradeCost integer not NULL default 150,

    CONSTRAINT pk_petsPlayerID primary key (playerID),
    CONSTRAINT fk_pets_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);


