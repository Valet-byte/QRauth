package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.action.Action;
import com.valet.qrmainserver.service.interfaces.DbService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DbServiceImpl implements DbService {

    private final KafkaTemplate<String, Action> template;

    @Override
    public boolean doAction(Action action) {
        try {
            template.send("doActionTopic", action);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
