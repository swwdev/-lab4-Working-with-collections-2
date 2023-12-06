package org.lab3;


import org.lab3.entities.Division;
import org.lab3.entities.Human;
import org.lab3.entities.Sex;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HumanParser {

    private HumanParser() {}

    private static final int ENG_ALPHABET_CARDINALITY = 26;
    static List<Division> divisionsCache = new ArrayList<>();

    static {
        for (int i = 0; i < ENG_ALPHABET_CARDINALITY; i++) {
            divisionsCache.add(new Division(i, Character.
                    toString((char)('A' + i))));
        }
    }

    static public Human parse(String [] str) throws IOException {
        if (str.length != 6)
            throw new IOException("parse exception");
        String [] regex = {"\\d+", "[A-Z][a-z]+", "(Male|Female)", "\\d{2}\\.\\d{2}\\.\\d{4}", "[A-Z]", "\\d+"};
        for (int i = 0; i < str.length; i++) {
            if(!str[i].matches(regex[i]))
                throw new IOException("parse exception");
        }
        return new Human(
                Integer.parseInt(str[0]),
                str[1],
                Sex.valueOf(str[2].toUpperCase()),
                    LocalDate.parse(str[3],DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                divisionsCache.get((str[4].charAt(0)) - 'A'),
                Integer.parseInt(str[5]));

    }
}
