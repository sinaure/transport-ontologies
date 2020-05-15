package com.sinaure.semantic.ontoReasoner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OntologyConfig {
    private static Logger logger = LogManager.getLogger(GraphdbService.class);

    @Autowired
    private OntologyProperties ontologyProperties;

    @Autowired
    private GraphdbService graphdbService;

    @Bean
    public OWLOntologyManager GetOWLOntologyManager() {
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        for (String file : ontologyProperties.getTransmodel()) {
            graphdbService.importOwl(SemanticUtil.getFileFromResources(file), man);
        }
        logger.info("Imported {} ontologies", man.getOntologies().size());
        return man;
    }


}