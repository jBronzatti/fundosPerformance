package rentabilidade.rentabilidademensal.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

interface IWriteTxtFile {
    void HashmapToTxtFile(HashMap<String, Float> map, String filePath);
}

public class WriteTxtFile implements IWriteTxtFile {
    public void HashmapToTxtFile(HashMap<String, Float> map, String filePath) {
        File file = new File(filePath);
        BufferedWriter bf = null;

        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file+"/saida.txt"));

            // iterate map entries
            for (Map.Entry<String, Float> entry :
                    map.entrySet()) {

                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
