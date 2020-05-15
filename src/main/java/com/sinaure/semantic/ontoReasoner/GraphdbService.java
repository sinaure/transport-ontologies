package com.sinaure.semantic.ontoReasoner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.stereotype.Service;

@Service
public class GraphdbService {
    private static Logger logger = LogManager.getLogger(GraphdbService.class);

    public void importTurtle(File f, String baseUri) {
        try {
            InputStream targetStream = new FileInputStream(f);
            SemanticUtil.parseTurtle(targetStream, baseUri, RDFFormat.TURTLE);
            targetStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void importOwl(File f, OWLOntologyManager man) {
        try {
            InputStream targetStream = new FileInputStream(f);
            man = SemanticUtil.parseOwl(f, man);
            targetStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
