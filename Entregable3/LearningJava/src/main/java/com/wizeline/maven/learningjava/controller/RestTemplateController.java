package com.wizeline.maven.learningjava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestTemplateController {

    private String url = "https://jsonplaceholder.typicode.com/posts";

    @GetMapping("/getApiPublic")
    public List<Object> consumoRest() {
        RestTemplate restTemplate = new RestTemplate();
        Object[] datoObtenido = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(datoObtenido);

    }
}
