package com.example.postgresdemo.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component //componente do spring. Só injetar a classe com @Autowired
public class DateUtil {

    public String formatLocalDateFormat(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }

}
