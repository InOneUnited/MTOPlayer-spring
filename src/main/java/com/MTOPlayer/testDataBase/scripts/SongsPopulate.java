package com.MTOPlayer.testDataBase.scripts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SongsPopulate {

    public void createFileWithSongQueries(int numOfQueries){
        List<String> queries = createQueries(numOfQueries);
        saveQueriesToFile(queries, "src/main/java/com.MTOPlayer.testDataBase/testData/songTableData/songFull.sql");
    }

    public void saveQueriesToFile(List<String> queries, String filePath){

        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);

            for(String query: queries){
                writer.write(query+"\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> createQueries(int numOfRows){
        CSVFileParser parser = new CSVFileParser();
        ApiKeyDao apiKeyDao = new ApiKeyDao();

        List<String> queriesList = new LinkedList<>();
        Map<Integer, List<Integer>> idsMap = parser.fileToMap("src/main/java/com.MTOPlayer.testDataBase/testData/songTableData/playlist_idAndApikey_idWithCommonLogin_id.csv");
        List<Integer> playlistIds = getListOfKeysFromMap(idsMap);

        int numOfPlaylistIds = playlistIds.size();

        for(int i=0; i<numOfRows; i++) {
            Integer randomPlaylistId = playlistIds.get(ThreadLocalRandom.current().nextInt(0, numOfPlaylistIds));

            List<Integer> apiIdsOfrandomPlayList = idsMap.get(randomPlaylistId);

            int numOfApiIdsOfRandomPlaylist = apiIdsOfrandomPlayList.size();

            int randomApiIdOfRandomPlaylist = apiIdsOfrandomPlayList.get(ThreadLocalRandom.current().nextInt(0, numOfApiIdsOfRandomPlaylist));

            String api_name = apiKeyDao.getApiName(randomApiIdOfRandomPlaylist);

            String songName = generateSongName(i);
            String songLink = generateSongLink(api_name, songName);

            String query = "INSERT INTO song (name, link, playlist_id, apikey_id) VALUES ('" + songName + "','" + songLink + "'," + randomPlaylistId + "," + randomApiIdOfRandomPlaylist +");";

            queriesList.add(query);

        }

        return queriesList;
    }

    private List<Integer> getListOfKeysFromMap(Map<Integer,List<Integer>> map){
        Map<Integer, List<Integer>> mapClone = new HashMap<>();
        mapClone.putAll(map);
        Iterator it = mapClone.entrySet().iterator();
        List<Integer> keys = new ArrayList<>();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Integer key = (Integer) pair.getKey();
            keys.add(key);
            it.remove(); // avoids a ConcurrentModificationException
        }

        return keys;
    }

    private String generateSongName(int num){
        String songName = "song_"+num;
        return songName;
    }

    private String generateSongLink(String serviceName, String songName){
        String link = "https://www."+serviceName+".com/"+songName;
        return link;
    }
}
