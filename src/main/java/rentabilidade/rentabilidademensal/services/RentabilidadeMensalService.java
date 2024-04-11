package rentabilidade.rentabilidademensal.services;
import org.springframework.stereotype.Service;
import rentabilidade.rentabilidademensal.utils.SortHashMap;
import rentabilidade.rentabilidademensal.utils.WriteTxtFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RentabilidadeMensalService {
    public void calcular(String[] args) throws IOException {
        var file = new InputStreamReader(
                this.getClass().getResourceAsStream("/rentabilidades.txt"));

        try (BufferedReader br = new BufferedReader(file)) {
            HashMap<String, Float> monthPerformanceList = new HashMap<>();
            SimpleDateFormat dateReaderFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", new Locale("pt","BR"));
            ArrayList<String> errorList = new ArrayList<String>();

            br.readLine(); // pulando a primeira linha (cabeçalho)
            String line = br.readLine();

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
                line = br.readLine();
            }

            var hashMapSorter = new SortHashMap();
            HashMap<String, Float> sortedMonthPerformanceList = hashMapSorter.sortByValueDesc(monthPerformanceList);

            var txtWriter = new WriteTxtFile();
            txtWriter.HashmapToTxtFile(sortedMonthPerformanceList, System.getProperty("user.dir")+"/..");

            System.out.println(errorList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
