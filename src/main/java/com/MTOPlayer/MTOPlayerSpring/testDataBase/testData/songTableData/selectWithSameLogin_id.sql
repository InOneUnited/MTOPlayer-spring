SELECT playlist.id AS playlist_id, apikey.id AS apikey_id FROM playlist INNER JOIN apikey
ON playlist.login_id = apikey.login_id

