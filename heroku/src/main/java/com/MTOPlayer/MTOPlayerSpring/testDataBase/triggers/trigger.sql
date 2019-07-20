CREATE OR REPLACE FUNCTION show_table_data(tableName text)
RETURNS void AS $show_login$
BEGIN
  SELECT * FROM tableName;
END;
$show_login$
LANGUAGE  plpgsql;

CREATE TRIGGER show_data BEFORE DELETE
ON login FOR each ROW
EXECUTE PROCEDURE show_table_data(login);
CREATE TRIGGER show_data BEFORE DELETE
ON apikey FOR each ROW
EXECUTE PROCEDURE show_table_data(apikey);
CREATE TRIGGER show_data BEFORE DELETE
ON password FOR each ROW
EXECUTE PROCEDURE show_table_data(password);
CREATE TRIGGER show_data BEFORE DELETE
ON playlist FOR each ROW
EXECUTE PROCEDURE show_table_data(playlist);
CREATE TRIGGER show_data BEFORE DELETE
ON salt FOR each ROW
EXECUTE PROCEDURE show_table_data(salt);
CREATE TRIGGER show_data BEFORE DELETE
ON session FOR each ROW
EXECUTE PROCEDURE show_table_data(session);
CREATE TRIGGER show_data BEFORE DELETE
ON song FOR each ROW
EXECUTE PROCEDURE show_table_data(song);
CREATE TRIGGER show_data BEFORE DELETE
ON user_info FOR each ROW
EXECUTE PROCEDURE show_table_data(user_info);

CREATE OR REPLACE FUNCTION create_login_backup()
RETURNS trigger AS $backup_login$
BEGIN
  IF tg_op = "DELETE" THEN
     SELECT * FROM login;
     INSERT INTO backup_login(id, login, email)
     VALUES (old.id, old.login, old.email);
     RETURN old;
  END IF;
  IF tg_op = "INSERT" THEN
     INSERT INTO backup_login(id, login, email)
     VALUES (new.id, new.login, new.email);
     RETURN new;
  END IF;
  IF tg_op = "UPDATE" THEN
     INSERT INTO backup_login(id, login, email)
     VALUES (old.id, old.login, old.email);
     RETURN new;
  END IF;
END;
$backup_login$
LANGUAGE plpgsql;

