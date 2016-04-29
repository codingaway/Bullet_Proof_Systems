/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Enda
 */
@Stateless
public class Logging implements LoggingLocal {

    /**
     * Resource location for messages
     */
    @Resource(mappedName = "java:app/LoggingQueue")
    private Queue java_appLoggingQueue;

    /**
     * Connection factory for messages
     */
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    /**
     * Send message to queue
     * @param message 
     */
    @Override
   public void sendMessageToQueue(String message){
       sendJMSMessageToLoggingQueue(message); }

   /**
    * Send message to log
    * @param messageData 
    */
    private void sendJMSMessageToLoggingQueue(String messageData) {
        context.createProducer().send(java_appLoggingQueue, messageData);
    }
   
   
}

