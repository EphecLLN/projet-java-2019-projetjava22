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
DELETE FROM team;

CREATE TABLE DBA.player (
    playerID integer default autoincrement,
    playerName long varchar not NULL,
    playerPassword long varchar not NULL,
    lastConnexion datetime NOT NULL DEFAULT getDate(),
    teamID integer not NULL default autoincrement,
    
    constraint pk_playerID primary KEY (playerID),
    CONSTRAINT fk_player_team FOREIGN KEY (teamID) REFERENCES team(teamID),
);
INSERT INTO player (playerName, playerPassword, teamID) VALUES ('InkMonster','abc123', 1),('MatthFlash','123abc', 2),('Swithan','abc123',1),('Gonogo','aaa111',3)

SELECT player.playerName, team.teamName FROM DBA.player NATURAL JOIN DBA.team WHERE player.teamID = 1;
SELECT player.playerName, team.teamName FROM DBA.player NATURAL JOIN DBA.team where team.teamMoney = (SELECT MAX(teamMoney) FROM DBA.team);

CREATE TABLE DBA.hero (
    playerID integer,
    gold integer default NULL,
    damage integer not NULL default 1,
    pets integer default NULL,
    artefactMoney integer default NULL,
    artefactID integer default NULL, 
    
    CONSTRAINT pk_statsPlayerID primary key (playerID),
    CONSTRAINT fk_hero_player FOREIGN KEY (playerID) REFERENCES player(playerID),
    CONSTRAINT fk_hero_artefact FOREIGN KEY (artefactID) REFERENCES artefact(artefactID)
);
INSERT INTO hero VALUES (1, 30, 2, 0, 0, 0),(2,75,4,5,0,3),(3,70,4,2,10,2),(4,250,12,10,55,4);
INSERT INTO hero VALUES (10, 1, 40, 13, 0, 0);
DELETE FROM hero WHERE playerID = 10;

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
INSERT INTO monster VALUES (3, 50, 100, 4, 10, 10, 30),(1, 10, 10, 1, 10, 6, 0),(2, 50, 100, 4, 10, 10, 30);
INSERT INTO monster VALUES (4, 10, 10, 1, 10, 6, 0);

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
INSERT INTO pets VALUES (2, 1, 1, 4, 250, 150),(1, 4, 1, 1, 150, 200);



DROP TABLE DBA.player;
DROP TABLE DBA.pets;
DROP TABLE DBA.team;
DROP TABLE DBA.artefact;
DROP TABLE DBA.hero;
DROP TABLE DBA.monster;

SELECT * FROM artefact
