package rentabilidade.rentabilidademensal.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger("rentabilidade");

    public void HashmapToTxtFile(HashMap<String, Float> map, String filePath) {
        File file = new File(filePath);
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file+"/saida.txt"));

            for (Map.Entry<String, Float> entry :
                    map.entrySet()) {

                // Separando chave e valor por ": "
                writer.write(entry.getKey() + ": "
                        + entry.getValue());

                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            logger.error("Erro ao escrever arquivo txt", e);
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                logger.error("Erro ao fechar writer", e);
            }
        }
    }
}
