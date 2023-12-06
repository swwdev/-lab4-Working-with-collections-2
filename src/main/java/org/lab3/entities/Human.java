package org.lab3.entities;

import lombok.Value;

import java.time.LocalDate;


@Value
public class Human {
    int id;
    String name;
    Sex sex;
    LocalDate birthday;
    Division division;
    int salary;

}
