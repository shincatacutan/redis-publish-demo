package com.markandshin.springboot.redis.redispubsubdemo;

public class PublishMessage {
  
  private String topic;
  private String message;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "PublishMessage [topic=" + topic + ", message=" + message + "]";
  }
}