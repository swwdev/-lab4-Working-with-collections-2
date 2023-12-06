package org.lab3;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.lab3.entities.Human;
import org.lab3.entities.Sex;


import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Stream;


class AppTest {
    @DisplayName("parse will be success if initial data is correct")
    @Test
    public void successParseIfCorrectCsv() throws IOException {
        String [] correctValues = {"101", "Name", "Male", "01.01.1000","E","0"};
        Human human = new Human(
                101,
                "Name",
                Sex.MALE,
                LocalDate.of(1000, 1, 1),
                HumanParser.divisionsCache.get(4),
                0
        );
        Assertions.assertThat(HumanParser.parse(correctValues)).isEqualTo(human);

    }
    @DisplayName("parse will be failed with IOException if not enough parameters passed")
    @Test
    public void throwIOExceptionIfNotEnoughParams() {
        org.junit.jupiter.api.Assertions.assertThrows(IOException.class,
                () -> HumanParser.parse(new String[] {"12","Name"}));
    }

    @DisplayName("parse will be failed with IOException if initial data is incorrect")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("getArgumentForToInvokeIOException")
    public void throwIOExceptionIfCsvFileIncorrect (String value1, String value2, String value3, String value4, String value5, String value6){
        org.junit.jupiter.api.Assertions.assertThrows(
                IOException.class,
                () -> HumanParser.parse(new String[]{value1,value2,value3,value4,value5,value6})
        );
    }
    static Stream<Arguments> getArgumentForToInvokeIOException (){
        return Stream.of(
                Arguments.of("dummy", "Aaleahya", "Female", "06.11.1949", "F", "1000"),
                Arguments.of("28283", "1212", "Female", "06.11.1949", "F", "1000"),
                Arguments.of("28283", "Aaleahya", "woman", "06.11.1949", "F", "1000"),
                Arguments.of("28283", "Aaleahya", "Female", "0.11.1949", "F", "1000"),
                Arguments.of("28283", "Aaleahya", "Female", "06.11.1949", "z", "1000"),
                Arguments.of("28283", "Aaleahya", "Female", "06.11.1949", "F", "dummy")
        );
    }

}
