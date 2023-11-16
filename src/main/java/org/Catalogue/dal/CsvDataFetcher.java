package org.Catalogue.dal;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvDataFetcher implements DataFetcher {
    private final String filePath;

    public CsvDataFetcher(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public Map<String, BigDecimal> getCatalogue() throws IOException, CsvException {
        Map<String,BigDecimal> catalogue = new HashMap<>();

        try(CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = csvReader.readAll();

            for (String[] row: rows){
                if(row.length >=2) {
                    catalogue.put(row[0], new BigDecimal(row[1]));
                }
            }
        }
        return catalogue;
    }
}
