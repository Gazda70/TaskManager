package com.example.UserTasks.populators;

import com.example.UserTasks.exceptions.DateNotValidException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for population related functionalities
 */
@Component
public class PopulationUtils {

    private static final String DATE_FORMAT = "dd mm yyyy";

    Date parseDate(final String dateString) throws DateNotValidException {
        if(dateString.isBlank()) {
            throw new DateNotValidException();
        }
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch(ParseException parseException) {
            throw new DateNotValidException();
        }
    }

    String dateToString(final Date date) throws DateNotValidException {
        if(date == null) {
            throw new DateNotValidException();
        }
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }
}
