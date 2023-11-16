package org.Catalogue.dal;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface DataFetcher {
    public Map<String, BigDecimal> getCatalogue() throws IOException, CsvException;
}
