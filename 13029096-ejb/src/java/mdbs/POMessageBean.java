/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdbs;

import admin.adminBeanLocal;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Enda
 */

@JMSDestinationDefinition(name = "java:app/LoggingQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "LoggingQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/LoggingQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})


public class POMessageBean implements MessageListener {

    /**
     * Injected Bean
     */
    @EJB
    private adminBeanLocal adminBean;
    
    private File logFile;
    private FileWriter wr;
    private boolean setPath;
    
    /**
     * Instantiate file object.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public POMessageBean() throws FileNotFoundException, IOException {
        logFile = new File("team8_logFile14.txt");
        setPath=false;
 
        
    }
    
    /**
     * Create logfile if not created.
     * Set file path.
     * Write incoming messages or exceptions to file 
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        
        TextMessage msg = (TextMessage) message;
        
        {
            try {
                if (!logFile.exists()) 
                {
                    logFile.createNewFile();    
                }
                
                if(!setPath)
                {
                    adminBean.setFilePath(logFile.getAbsolutePath());
                    setPath=true;
                }
                
                if(!msg.getText().equals(""))
                {
                    wr = new FileWriter(logFile,true);
                    wr.write(msg.getText());
                    wr.write(System.lineSeparator());
                    wr.close();
                }
                
            } catch (Exception ex) {
                try {
                    wr.write(ex.toString());
                } catch (IOException ex1) {
                    Logger.getLogger(POMessageBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(POMessageBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    
}