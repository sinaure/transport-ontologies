package com.sinaure.semantic.ontoReasoner;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.sinaure.semantic.ontoReasoner"})
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    private static Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private GraphdbService graphdbService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        for (int i = 0; i < args.length; i++) {

            File dir = SemanticUtil.getFileFromResources(args[i]);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                logger.info("folder: {}", args[i]);
                for (File f : directoryListing) {
                    logger.info("import ontology {} to graphdb", f.getName());
                    try {
                        InputStream targetStream = new FileInputStream(f);
                        if (dir.getName().equalsIgnoreCase("gtfs")) {
                            // SemanticUtil.parseTurtle(targetStream, dir.getName(),
                            // RDFFormat.TURTLE);
                        } else {
                            // SemanticUtil.parseOwl(f);
                        }

                        targetStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                logger.info("{} not a directory", args[i]);
            }
        }
    }


}
