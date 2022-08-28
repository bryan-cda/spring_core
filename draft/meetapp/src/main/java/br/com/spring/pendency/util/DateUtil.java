package br.com.spring.pendency.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    public String getCurrentLocalDateTime(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss").format(localDateTime);
    }
}
