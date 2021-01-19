package io.github.rahulrajsonu.springretry.resource;

import io.github.rahulrajsonu.springretry.service.impl.MyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.UndeclaredThrowableException;

@RestController
@Slf4j
public class ApiResource {

    @Autowired
    private MyServiceImpl service;

    @GetMapping(value="/", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity invoke(){
        try {
            this.service.retryService("Invoke");
        } catch (Exception e) {
            if(e instanceof UndeclaredThrowableException){
                System.out.println("Top level-> "+e.getMessage());
                System.out.println("Nested--> "+((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage());
            }else {
                e.printStackTrace();
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public void invokeHelper() throws Exception{
        this.service.retryService("Invoke");
        log.info("Executing invoke helper------->");
    }
}
