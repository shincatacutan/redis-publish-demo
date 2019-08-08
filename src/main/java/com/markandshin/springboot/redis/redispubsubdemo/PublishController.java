package com.markandshin.springboot.redis.redispubsubdemo;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
@CrossOrigin(origins = "*")
public class PublishController {
  
  @Value("${redis.config.path:file:redis-config.yaml}")
  private String redisConfigPath;
  @Autowired
  private ResourceLoader resourceLoader;
  private RedissonClient client;
  
  @PostConstruct
  public void init() {
    Config config;
    try {
      config = Config.fromYAML(resourceLoader.getResource(redisConfigPath).getInputStream());
      client = Redisson.create(config);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
 
  @PostMapping(path = "/publish", produces = "application/json")
  public ResponseEntity<Object> publisher(@RequestBody PublishMessage message) {
    RTopic publishTopic = client.getTopic(message.getTopic());
    publishTopic.publish(message.getMessage());
    return ResponseEntity.ok("Posted!");
  }
}
