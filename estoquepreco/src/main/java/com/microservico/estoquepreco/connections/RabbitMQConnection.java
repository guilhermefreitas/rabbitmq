package com.microservico.estoquepreco.connections;

import constantes.RabbitMQConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        Binding binding = new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
        return binding;
    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitMQConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQConstantes.FILA_PRECO);
        Queue filaEstoque2 = this.fila("MINHA_FILA");

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = relacionamento(filaEstoque, troca);
        Binding ligEstoque2 = relacionamento(filaEstoque2, troca);

        Binding ligacaoPreco = relacionamento(filaPreco, troca);


        //criando as filas no rabbitmq
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);
        this.amqpAdmin.declareQueue(filaEstoque2);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
        this.amqpAdmin.declareBinding(ligEstoque2);


    }

}
