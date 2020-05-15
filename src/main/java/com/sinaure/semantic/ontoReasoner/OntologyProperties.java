package com.sinaure.semantic.ontoReasoner;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("ontologies")
public class OntologyProperties {
    private List<String> gtfs;
    private List<String> transmodel;

    public List<String> getGtfs() {
        return gtfs;
    }

    public void setGtfs(List<String> gtfs) {
        this.gtfs = gtfs;
    }

    public List<String> getTransmodel() {
        return transmodel;
    }

    public void setTransmodel(List<String> transmodel) {
        this.transmodel = transmodel;
    }
}
