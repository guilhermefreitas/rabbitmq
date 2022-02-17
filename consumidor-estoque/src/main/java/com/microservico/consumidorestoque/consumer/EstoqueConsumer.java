package com.microservico.consumidorestoque.consumer;

import constantes.RabbitMQConstantes;
import dto.EstoqueDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDto estoqueDto){
        System.out.println(estoqueDto.codigoProduto);
        System.out.println(String.valueOf(estoqueDto.quantidade));
        System.out.println("------------------------------");

    }
}
