CREATE TABLE DBA.artefact (
    artefactID integer not NULL default autoincrement,
    artefactName long varchar not NULL,
    
    CONSTRAINT pk_artefactID primary key (artefactID)
);
INSERT INTO artefact (artefactName) VALUES ('doublePet'),('doubleDMG'),('plus5DMG'),('minus1Boss'),('every10Hit');


CREATE TABLE DBA.team (
    teamID integer not NULL default autoincrement,
    teamName long varchar not NULL,
    teamMoney integer default NULL,
    teamPets integer default NULL,

    CONSTRAINT pk_teamID primary key (teamID)
);
INSERT INTO team (teamName, teamMoney, teamPets) VALUES ('Var Motiv=0', 100, 2),('Var Motiv=1', 75, 5),('Var Motiv=2', 250, 10);

CREATE TABLE DBA.player (
    playerID integer default autoincrement,
    playerName long varchar not NULL,
    playerPassword long varchar not NULL,
    lastConnexion datetime NOT NULL DEFAULT getDate(),
    teamID integer not NULL default autoincrement,
    
    constraint pk_playerID primary KEY (playerID),
    CONSTRAINT fk_player_team FOREIGN KEY (teamID) REFERENCES team(teamID)
);
INSERT INTO player (playerName, playerPassword, teamID) VALUES

CREATE TABLE DBA.hero (
    playerID integer default autoincrement,
    gold integer default NULL,
    damage integer not NULL default 1,
    pets integer default NULL,
    artefactMoney integer default NULL,
    artefactID integer NOT NULL default autoincrement, 
    
    CONSTRAINT pk_statsPlayerID primary key (playerID),
    CONSTRAINT fk_stats_player FOREIGN KEY (playerID) REFERENCES player(playerID),
    CONSTRAINT fk_artefact_player FOREIGN KEY (artefactID) REFERENCES artefact(artefactID)
);
INSERT INTO hero VALUES

CREATE TABLE DBA.monster (
    playerID integer not NULL default autoincrement,
    pv integer not NULL default 10,
    "add" integer not NULL default 10,
    number integer not NULL default 1,
    boss integer not NULL default 10,
    goldIncrease integer not NULL default 6,
    wave integer default NULL,

    CONSTRAINT pk_monsterPlayerID primary key (playerID),
    CONSTRAINT fk_monster_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);
INSERT INTO monster VALUES

CREATE TABLE DBA.pets (
    playerID integer default autoincrement,
    damage integer not NULL default 1,
    "add" integer not NULL default 1,
    amount integer default NULL,
    buyCost integer not NULL default 100,
    upgradeCost integer not NULL default 150,

    CONSTRAINT pk_petsPlayerID primary key (playerID),
    CONSTRAINT fk_pets_player FOREIGN KEY (playerID) REFERENCES player(playerID)
);
INSERT INTO pets VALUES




INSERT INTO team (teamName) VALUES ('Motiv') WHERE teamName != 'Motiv';

SELECT * FROM team;

INSERT INTO player (playerName, playerPassword) VALUES ('Swithan', 'sql')
SELECT * FROM player;


DROP TABLE DBA.player;
DROP TABLE DBA.pets;
DROP TABLE DBA.team;
DROP TABLE DBA.artefact;
DROP TABLE DBA.hero;
DROP TABLE DBA.monster;


