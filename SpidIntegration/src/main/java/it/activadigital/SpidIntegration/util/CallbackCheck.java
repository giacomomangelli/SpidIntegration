package it.activadigital.SpidIntegration.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Getter
@Slf4j
public class CallbackCheck extends ApplicationEvent {

    private String message;

    public CallbackCheck(Object source, String message) {
        super(source);
        this.message = message;
    }


}
