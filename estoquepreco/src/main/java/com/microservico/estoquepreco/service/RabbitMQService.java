package com.microservico.estoquepreco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate template;

    public void enviaMensagem(String nomeFila, Object mensagem){
        template.convertAndSend(nomeFila, mensagem);
    }
}
