CREATE TABLE organizations (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100)
) ENGINE=InnoDB;

ALTER TABLE teams ADD COLUMN organization_id INTEGER;
ALTER TABLE teams ADD CONSTRAINT fk_teams_organization FOREIGN KEY (organization_id) REFERENCES organizations (id);
