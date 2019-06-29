package com.notification.processor;

import com.notification.service.INotification;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Scope("singleton")
public final class QueueProcessor {
    public volatile boolean KILL = false;
    private Consumer consumer = new Consumer();
    //Commenting out this code since same functionality is achieved via Spring boot Singleton scope

   /* private static QueueProcessor queueProcessor;
    private QueueProcessor() { }
    public static synchronized QueueProcessor getQueueProcessor(){
        //Lazy Initialization
        if(queueProcessor == null){
            queueProcessor = new QueueProcessor();
            Logger.getLogger(QueueProcessor.class.getName()).log(Level.INFO, "New QueueProcessor is created for the notification system");

            return queueProcessor;
        }
    }*/

   private final int processors = Runtime.getRuntime().availableProcessors();
   private BlockingQueue sharedQueue = new LinkedBlockingQueue(processors*4);

   public void addQueueProcessor(INotification notification) throws InterruptedException{
       sharedQueue.put(notification);
   }

   public Consumer getConsumer(){
       return consumer;
   }

   class Consumer implements Runnable {

       @Override
       public void run() {
           while(!KILL){
               try{
                   INotification notification =(INotification) sharedQueue.take();
                   notification.sendMessage();
               }catch(InterruptedException e1){
                   Logger.getLogger(QueueProcessor.Consumer.class.getName()).log(Level.SEVERE, "Consumer is not able to pick up the notification message", e1);
               }catch(MessagingException e2){
                   e2.printStackTrace();
               }catch(IOException e3){
                   e3.printStackTrace();
               }

           }
       }
   }

}
