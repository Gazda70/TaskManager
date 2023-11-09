package com.example.UserTasks.populators;

import com.example.UserTasks.exceptions.DateNotValidException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PopulationUtils {

    private static final String DATE_FORMAT = "dd mm yyyy";

    Date parseDate(final String dateString) throws DateNotValidException, ParseException {
        if(dateString.isBlank()) {
            throw new DateNotValidException();
        }
        return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
    }
}
