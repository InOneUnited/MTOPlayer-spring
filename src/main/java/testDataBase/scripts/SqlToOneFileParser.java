package testDataBase.scripts;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SqlToOneFileParser {
    public String parser(List<String> filesNames) throws FileNotFoundException {
        String allFiles="";
        for(String fileName: filesNames) {
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                allFiles += sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return allFiles;


    }

    public void saveStringToFile(String toFile, String fileName){
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(toFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> getFilesNames(String directory){
        List<String> results = new ArrayList<String>();

        File[] files = new File(directory).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.add(directory+"/"+file.getName());
            }
        }

        Collections.sort(results);
        results.sort(Comparator.comparingInt(String::length));

        return results;
    }
}
