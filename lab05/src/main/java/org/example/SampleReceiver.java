package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.entity.Student;

import javax.jms.*;
import java.util.ArrayList;


public class SampleReceiver {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory("tcp://localhost:61616");

        ArrayList<String> lst=new ArrayList<>();
        lst.add("org.example.entity");
        //trust obj
        factory.setTrustedPackages(lst);

        Connection connection=factory.createConnection("admin","admin");
        Session session= connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        connection.start();

        Destination destination= session.createQueue("dynamicQueues/tienhuong");
        MessageConsumer messageConsumer= session.createConsumer(destination);
        System.out.println("Waiting...");
        messageConsumer.setMessageListener(message -> {
            try{
                if(message instanceof TextMessage){
                    String msg=((TextMessage) message).getText();
                    System.out.println(msg);
                }else if( message instanceof ObjectMessage){
                    Student st=message.getBody(Student.class);
                    System.out.println(st);
                }
                else {
                    System.out.println("Not know ");
                }
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
