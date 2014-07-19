package com.thilinamb.mqtt.client.sub;

import com.thilinamb.mqtt.client.AbstractMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MQTT subscriber implementation using Eclipse Paho MQTT client lib.
 * Author: Thilina
 * Date: 7/19/14
 */
public class Subscriber extends AbstractMqttClient{

    public static final String BROKER_URL = "tcp://test.mosquitto.org:1883";
    public static final String TOPIC = "thilinamb/test";
    private static final String CLIENT_ID = "thilinamb-subscriber";

    private static final Logger logger = Logger.getLogger(Subscriber.class.getName());

    public Subscriber(String brokerUrl, String clientId, String topic) throws MqttException {
        super(brokerUrl, clientId, new SubscriberCallback());
        try {
            // subscribe
            getClient().subscribe(topic);
            logger.info("Connected to " + brokerUrl + " and subscribed to topic " + topic);
        } catch (MqttException e) {
            logger.log(Level.SEVERE, "Error instantiating the publisher", e);
            throw e;
        }
    }

    public static void main(String[] args) throws MqttException {
        Subscriber subscriber = new Subscriber(BROKER_URL, CLIENT_ID, TOPIC);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.trim().toLowerCase().equals("quit")){
                break;
            }
        }
        subscriber.disconnect();
    }

}
