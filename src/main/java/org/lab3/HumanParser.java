package org.lab3;

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

    static public Human parse(String [] str) {

        return new Human(
                Integer.parseInt(str[0]),
                str[1],
                Sex.valueOf(str[2].toUpperCase()),
                    LocalDate.parse(str[3],DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                divisionsCache.get((str[4].charAt(0)) - 'A'),
                Integer.parseInt(str[5]));

    }
}
