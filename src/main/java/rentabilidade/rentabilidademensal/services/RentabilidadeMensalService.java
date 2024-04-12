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
        var file = new InputStreamReader(
                this.getClass().getResourceAsStream("/rentabilidades.txt"));

        try (BufferedReader reader = new BufferedReader(file)) {
            HashMap<String, Float> monthPerformanceList = new HashMap<>();
            SimpleDateFormat dateReaderFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", new Locale("pt","BR"));
            ArrayList<String> errorList = new ArrayList<String>();

            reader.readLine(); // pulando a primeira linha (cabeçalho)
            String line = reader.readLine();

            while (line != null) {
                String[] values = line.split(";");
                try {
                    Date date = dateReaderFormat.parse(values[0]);
                    String month = monthFormat.format(date).toLowerCase();
                    Float performance = Float.valueOf(values[1]);
                    monthPerformanceList.put(month, monthPerformanceList.getOrDefault(month, 0f) + performance);
                } catch (ParseException pe) {
                    errorList.add(pe.getMessage());
                }
                line = reader.readLine();
            }
            reader.close();

            var hashMapSorter = new SortHashMap();
            HashMap<String, Float> sortedMonthPerformanceList = hashMapSorter.sortByValueDesc(monthPerformanceList);

            var txtWriter = new WriteTxtFile();
            txtWriter.HashmapToTxtFile(sortedMonthPerformanceList, System.getProperty("user.dir")+"/src/main/resources");

            if(!errorList.isEmpty()){
                logger.error(errorList);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Cálculo de Rentabilidade Mensal finalizado");
    }
}
