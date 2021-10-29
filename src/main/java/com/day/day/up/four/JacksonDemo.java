package com.day.day.up.four;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JacksonDemo {

    public static void main(String[] args) throws ParseException, JsonProcessingException {
        User user = new User();
       // user.setName("小民");
        user.setEmail("xiaomin@sina.com");
        user.setAge(20);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateformat.parse("1996-10-01"));

        ObjectMapper mapper = new ObjectMapper();

        //User类转JSON
        //输出结果：{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
    }
}
