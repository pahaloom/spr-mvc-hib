CREATE TABLE members (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE members_teams (
  member_id INTEGER NOT NULL,
  team_id INTEGER NOT NULL
) ENGINE=InnoDB;

ALTER TABLE members_teams ADD UNIQUE INDEX uq_members_teams (member_id, team_id);
ALTER TABLE members_teams ADD CONSTRAINT fk_members_teams_member FOREIGN KEY (member_id) REFERENCES members (id);
ALTER TABLE members_teams ADD CONSTRAINT fk_members_teams_team FOREIGN KEY (team_id) REFERENCES teams (id);
