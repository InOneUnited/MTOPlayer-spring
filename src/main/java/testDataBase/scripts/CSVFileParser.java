package testDataBase.scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVFileParser {
    public Map<Integer, List<Integer>> fileToMap(String filePath){
        String csvFile = filePath;

        String line = "";
        String csvSplitBy = ",";

        Map<Integer, List<Integer>> ids = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<Integer> values = null;
            List<Integer> keys = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] stringIds = line.split(csvSplitBy);

                Integer newKey = Integer.parseInt(stringIds[0]);
                Integer newValue = Integer.parseInt(stringIds[1]);

                boolean exists = checkIfInList(keys, newKey);

                if(exists){
                    values.add(newValue);

                }else {
                    keys.add(newKey);

                    values = new LinkedList<>();
                    values.add(newValue);
                }

                ids.put(newKey, values);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ids;
    }

    public boolean checkIfInList(List<Integer> keys,Integer keyToCheck){
        for(Integer key: keys){
            if(key.equals(keyToCheck)){
                return true;
            }
        }
        return false;
    }
}
