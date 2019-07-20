package com.MTOPlayer.testDataBase.scripts;

public class Run {
    static SqlToOneFileParser parser = new SqlToOneFileParser();

    public static void main(String[] args){

        //SQLTOONE FILE

//        List<String> filesNames = new ArrayList();
//
//        filesNames = parser.getFilesNames("src/main/java/com.MTOPlayer.testDataBase/testData/passwordTableData");
//
//        String wholeFile = "";
//
//        try {
//            wholeFile = parser.parser(filesNames);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        parser.saveStringToFile(wholeFile, "src/main/java/com.MTOPlayer.testDataBase/testData/passwordTableData/passwordFull.sql");

        //CREATE SONG TEST DATA
//        SongsPopulate songs = new SongsPopulate();
//        songs.createFileWithSongQueries(50000);
    }
}
