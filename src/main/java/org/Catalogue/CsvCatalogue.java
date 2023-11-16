package org.Catalogue;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CsvCatalogue extends Catalogue{
    private String filePath = "data.csv";
    public CsvCatalogue(String filePath) throws IOException, CsvException {
        this.filePath = filePath;
        getCatalogue();
    }

    public CsvCatalogue() throws IOException, CsvException {
        getCatalogue();
    }

    private void getCatalogue() throws IOException, CsvException {

        try(CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = csvReader.readAll();

            for (String[] row: rows){
                if(row.length >=2) {
                    itemsList.put(row[0], new BigDecimal(row[1]));
                }
            }
        }
    }
}
