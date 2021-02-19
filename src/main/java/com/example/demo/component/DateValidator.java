package com.example.demo.component;

import java.time.LocalDate;
import java.util.List;

public interface DateValidator {

    boolean isValid (String dateStr);
    LocalDate strToLocalDate(String dateStr);

}
