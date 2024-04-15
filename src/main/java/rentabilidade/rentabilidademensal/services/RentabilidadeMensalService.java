package rentabilidade.rentabilidademensal.services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import rentabilidade.rentabilidademensal.utils.SortHashMap;
import rentabilidade.rentabilidademensal.utils.WriteTxtFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RentabilidadeMensalService {

    private static final Logger logger = LogManager.getLogger(RentabilidadeMensalService.class);

    public void calcularRentabilidadeMensal(String[] args) throws IOException {

        logger.info("Calculando Rentabilidade Mensal");
        try (InputStreamReader file = new InputStreamReader(this.getClass().getResourceAsStream("/rentabilidades.txt"));
             BufferedReader reader = new BufferedReader(file)) {

            HashMap<String, Float> monthPerformanceList = new HashMap<>();

            List<String> errorList = processLines(reader, monthPerformanceList);

            var hashMapSorter = new SortHashMap();
            HashMap<String, Float> sortedMonthPerformanceList = hashMapSorter.sortByValueDesc(monthPerformanceList);

            var txtWriter = new WriteTxtFile();
            txtWriter.HashmapToTxtFile(sortedMonthPerformanceList, System.getProperty("user.dir")+"/src/main/resources");

            if(!errorList.isEmpty()){
                logger.error(errorList);
            }
        } catch (IOException e) {
            logger.error("Falha ao processar o arquivo: {}",e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Cálculo de Rentabilidade Mensal finalizado");
    }

    private List<String> processLines(BufferedReader reader, Map<String, Float> monthPerformanceList) throws IOException {
        SimpleDateFormat dateReaderFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", new Locale("pt", "BR"));
        List<String> errorList = new ArrayList<>();

        reader.readLine(); // Pulando a primeira linha (cabeçalho)
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                String[] values = line.split(";");
                Date date = dateReaderFormat.parse(values[0]);
                String month = monthFormat.format(date).toLowerCase();
                Float performance = Float.valueOf(values[1]);
                monthPerformanceList.put(month, monthPerformanceList.getOrDefault(month, 0f) + performance);
            } catch (ParseException pe) {
                errorList.add("Erro ao ler a linha: " + line + "; Error: " + pe.getMessage());
            }
        }
        return errorList;
    }
}
