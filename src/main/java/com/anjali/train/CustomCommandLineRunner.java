package com.anjali.train;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CustomCommandLineRunner implements CommandLineRunner {
   private static final Logger LOG = LoggerFactory.getLogger(CustomCommandLineRunner.class);
   @Autowired
   private DataLoader dataBuffer;
   @Override
   public void run(String...args) throws Exception {
       LOG.info("Loading up the entire database");
       dataBuffer.createStationMap();
   }
}