INSERT INTO users (username, password, enabled)
	values('user','pass',true);
	
INSERT INTO users (username, password, enabled)
	values('admin','pass',true);
	
INSERT INTO authorities(username, authority)
	values('user', 'ROLE_USER');
	
INSERT INTO authorities(username, authority)
	values('admin', 'ROLE_ADMIN');

INSERT INTO user (id, active, password, role, username, acc_holder_id)
	values(1,true,'admin','role_admin','admin',null);