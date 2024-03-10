package org.example;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.entity.Student;

import java.util.ArrayList;

public class SampleSender {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        ArrayList<String>lst=new ArrayList<>();
        lst.add("org.example.entity");
        //trust obj
        factory.setTrustedPackages(lst);
        try{
            Connection connection=factory.createConnection("admin","admin");
            Session session= connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            connection.start();

            Destination destination= session.createQueue("dynamicQueues/tienhuong");
            MessageConsumer messageConsumer= session.createConsumer(destination);
            MessageProducer messageProducer= session.createProducer(destination);
//            for(int i=0;i<10;i++){
//                TextMessage textMessage= session.createTextMessage("Messange "+i);
//                messageProducer.send(textMessage);
//            }
            Student student=new Student(2883, "Lan Huong");
            ObjectMessage objectMessage=session.createObjectMessage(student);
            messageProducer.send(objectMessage);
            System.out.println("Sent");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
