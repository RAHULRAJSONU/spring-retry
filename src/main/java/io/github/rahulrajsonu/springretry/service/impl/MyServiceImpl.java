package io.github.rahulrajsonu.springretry.service.impl;

import io.github.rahulrajsonu.springretry.exception.ServiceException;
import io.github.rahulrajsonu.springretry.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyServiceImpl implements MyService {
    volatile int count = 0;
    @Override
    public long retryService(String sql) throws Exception {
       log.info("Executing [retryService] for {} times",count);
       this.count++;
       try{
           this.validate();
       }catch (Exception e){
           log.error(e.getLocalizedMessage());
           throw e;
       }
       log.info("Validated successfully!!!!");
       return 1l;
    }

    @Override
    public long recover(Exception e, String sql) throws Exception {
        log.info("Running recovery for ServiceException, count is {}",this.count);
        throw e;
    }

    public void validate() throws Exception {
        log.info("Validation, count is {}",this.count);
        if (this.count > 2){
            throw new ServiceException("Throwing for count: " + this.count);
        }else {
            throw new Exception("Throwing Exception");
        }
    }
}
