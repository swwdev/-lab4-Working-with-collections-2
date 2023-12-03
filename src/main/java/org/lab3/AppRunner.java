package org.lab3;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.util.List;
import java.io.*;
import java.util.ArrayList;


public class AppRunner {
    public static void main(String[] args) throws IOException, CsvValidationException {
        List <Human> humanList = new ArrayList<>();
        String csvFilePath = "foreign_names.csv";
        char separator = ';';
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(separator)
                .build();
        try (InputStream in = AppRunner.class.getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader csvReader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in))
                     .withCSVParser(parser)
                     .build()) {
            if (csvReader == null)
                throw new EOFException();
            String[] nextLine;
            if (csvReader.readNext() == null)
                throw new IOException("empty file");
            int count = 0;
            while ((nextLine = csvReader.readNext()) != null) {
                humanList.add(HumanParser.parse(nextLine));
                System.out.println(++count);
            }
            System.out.println();
        }
    }
}
