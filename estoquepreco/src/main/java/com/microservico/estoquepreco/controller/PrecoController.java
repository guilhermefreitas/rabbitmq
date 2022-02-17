package com.microservico.estoquepreco.controller;


import dto.PrecoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preco")
public class PrecoController {

    private ResponseEntity alteraPreco(@RequestBody PrecoDto precoDto){
        return ResponseEntity.ok().build();
    }
}
