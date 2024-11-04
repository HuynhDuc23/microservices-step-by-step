package jmaster.io.accountservice.client;

import jmaster.io.accountservice.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="notification-service", fallback = NotificationServiceImpl.class)
public interface NotificationService {
    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody MessageDTO messageDTO) ;
}
@Component
class NotificationServiceImpl implements NotificationService {
    Logger loger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendNotification(MessageDTO messageDTO) {
        // fallback
        loger.error("NotificationService is slow");
    }
}
