CREATE INDEX users_birthday
ON user_info (birthday);

CREATE INDEX active_users_ip_index
ON session (ip);

CREATE INDEX new_users
ON user_info (id, birthday) WHERE birthday >= '01/01/2019';

CREATE INDEX song_names
ON song (name)

CREATE INDEX playlist_names
ON playlist (name)
