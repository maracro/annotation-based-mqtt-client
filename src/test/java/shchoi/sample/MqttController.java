package shchoi.sample;

import org.springframework.stereotype.Controller;
import shchoi.mqtt.MqttSender;
import shchoi.mqtt.annotation.MqttListener;
import shchoi.mqtt.annotation.TopicVariable;

@Controller
public class MqttController {
    private final MqttSender mqttSender;

    public MqttController(MqttSender mqttSender) {
        this.mqttSender = mqttSender;
    }

    @MqttListener(topic = "/example/topic", qos = 0)
    public void subscribe(String payload) {
//        log.info(payload);
    }

    @MqttListener(topic = "/example/+/a")
    public void subscribeWithWildCard(String payload, @TopicVariable String topicVariable) {
//        log.info(payload);
//        log.info(topicVariable);
    }

    @MqttListener(topic = "/+/+/a")
    public void subscribeWithMultipleWildCard(String payload,
                                              @TopicVariable(index = 0) String firstVariable,
                                              @TopicVariable(index = 1) String secondVariable) {
//        log.info(payload);
//        log.info(firstVariable);
//        log.info(secondVariable);
    }

    @MqttListener(topic = "/example")
    public void subscribeAndPublish(Person person) {
//        log.info("{}", person);
        String topic = "/topic";
        int qos = 2;
        boolean retained = true;

        mqttSender.sendMessage(topic, person, qos, retained);
    }

    private class Person {
        private Long id;
        private String name;
        private String address;
        private String email;
    }
}
