INSERT INTO organizations(id, name) VALUES(20, 'Test Organization');
INSERT INTO teams(id, name, rating, organization_id) VALUES(101, 'Test Team', 32, 20);
INSERT INTO teams(id, name, rating, organization_id) VALUES(102, 'Test Team 2', 32, 20);
INSERT INTO members(id, name) VALUES(100, 'Test Member');
INSERT INTO members_teams(member_id, team_id) VALUES(100, 101);
INSERT INTO members_teams(member_id, team_id) VALUES(100, 102);
