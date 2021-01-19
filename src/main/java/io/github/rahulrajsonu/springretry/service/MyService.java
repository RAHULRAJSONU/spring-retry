package io.github.rahulrajsonu.springretry.service;

import io.github.rahulrajsonu.springretry.exception.ServiceException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

public interface MyService { 
  @Retryable( value = {ServiceException.class}, maxAttempts = 3,
            backoff = @Backoff(delay = 1))
  long retryService(String name) throws Exception;

  @Recover
  long recover(Exception e, String name) throws Exception;

}