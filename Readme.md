# download jars and add to plugin folder
```
https://github.com/neo4j-contrib/neo4j-apoc-procedures
https://github.com/neo4j-labs/neosemantics
```
# 127.0.0.1:7474
```
MATCH (resource:Resource) DETACH DELETE resource;
MATCH (resource:_NsPrefDef) DETACH DELETE resource;
CALL n10s.graphconfig.init();

CALL n10s.nsprefixes.add("gtfs", "http://vocab.gtfs.org/terms#");
CALL n10s.nsprefixes.add("transmodel_facilities", "https://w3id.org/transmodel/facilities#");
CALL n10s.nsprefixes.add("transmodel_journeys", "https://w3id.org/transmodel/journeys#");

call n10s.rdf.import.fetch( "file:///import/ontologies/gtfs/gtfs.ttl",
                            "Turtle");
call n10s.rdf.import.fetch( "file:///import/ontologies/transmodel/tm-commons.owl",
                            "RDF/XML");
call n10s.rdf.import.fetch( "file:///import/ontologies/transmodel/tm-facilities.owl",
                            "RDF/XML");
call n10s.rdf.import.fetch( "file:///import/ontologies/transmodel/tm-fares.owl",
                            "RDF/XML");  
call n10s.rdf.import.fetch( "file:///import/ontologies/transmodel/tm-journeys.owl",
                            "RDF/XML");  
call n10s.rdf.import.fetch( "file:///import/ontologies/transmodel/tm-organizations.owl",
                            "RDF/XML");                                             
```
                        
# TODO Reasoning Owl API
https://neo4j.com/blog/using-owl-with-neo4j/                                                                                                              