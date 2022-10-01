package com.valet.qrdbserver.listeners;

import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.service.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActionListener {

    private final ActionService actionService;

    @KafkaListener(topics = "doActionTopic")
    public void doAction(@Payload Action action){
        actionService.doAction(action);
    }

}
