package com.swimpool.swim.pool;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.swimpool.swim.pool.DTO.SignUpRequest;

public class SignUpRequestTest {

    @Test
    void createTest(){
        final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^\\+?\\d{10,15}$");
        var sample = "+791931314214";
        var matcher = VALID_PHONE_NUMBER_REGEX.matcher(sample);
        //assertTrue(matcher.matches());
        var request = new SignUpRequest();
        assertTrue(request.setPhone(sample));
        
    }
    
    @Test
    void timeTest(){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        LocalDate sample = date.toLocalDateTime().toLocalDate();
        //sample.minusHours()
        LocalTime hour = LocalTime.of(10,0);
        var tmp  = sample.atStartOfDay().plus(10, ChronoUnit.HOURS);
        var ldt = date.toLocalDateTime();
        System.out.println(tmp.toString());
        System.out.println(ldt.with(LocalTime.MIN));
    }
}
