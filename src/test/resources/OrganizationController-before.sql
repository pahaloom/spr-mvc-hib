INSERT INTO organizations(id, name) VALUES(20, 'test');
INSERT INTO teams(id, name, rating, organization_id) VALUES(10, 'Testing cascade', 32, 20);
INSERT INTO members(id, name) VALUES(100, 'Not cascading memger 1');
INSERT INTO members_teams(member_id, team_id) VALUES (100, 10);
