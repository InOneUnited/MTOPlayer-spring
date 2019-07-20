1. chce zobaczyć login, email i ip
CREATE VIEW ACTIVE_USERS_IP AS
SELECT login.login, login.email, session.ip FROM login
INNER JOIN session
ON login.id = session.login_id;

2. chce zobaczyć api_name, api_username, song_name, playlist_name
CREATE VIEW SHOWED_SONG_INFO AS
SELECT apikey.api_name, apikey.api_username, song.name AS song_name, playlist.name AS playlist_name FROM apikey
INNER JOIN playlist
ON apikey.login_id = playlist.login_id
INNER JOIN song
ON playlist.id = song.playlist_id

3. chce zobaczyć login, first_name, last_name i birthday
CREATE VIEW USERS_NAME_AND_BIRTHDAY AS
SELECT login.login, user_info.first_name, user_info.last_name, user_info.birthday
FROM login INNER JOIN user_info
ON login.id = user_info.login_id
WHERE user_info.first_name NOTNULL AND user_info.last_name NOTNULL AND user_info.birthday NOTNULL
ORDER BY birthday

4. chce zobaczyć login, liczbe playlist i liczbe piosenek w każdej
CREATE VIEW NUM_OF_PLAYLISTS_AND_SONGS_BY_USER  AS
SELECT login.login, COUNT(DISTINCT playlist.id) AS numberOfPlaylists, COUNT(song.id) AS numberOfSongs
FROM login INNER JOIN playlist
ON login.id = playlist.login_id
LEFT JOIN song
ON playlist.id = song.playlist_id
GROUP BY login
ORDER BY login
