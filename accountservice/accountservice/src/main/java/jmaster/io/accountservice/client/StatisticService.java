package jmaster.io.accountservice.client;

import jmaster.io.accountservice.model.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@FeignClient(name = "statistic-service" , fallback = Statistic.class)
public interface StatisticService {
    @PostMapping("/statistic")
    public void add(@RequestBody StatisticDTO statisticDTO);
}
@Component
class Statistic implements StatisticService{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void add(StatisticDTO statisticDTO) {
          //fallback
          logger.error("Statistic errors server is slow");
    }
}
