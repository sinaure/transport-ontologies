# download jars and add to plugin folder
```
https://github.com/neo4j-contrib/neo4j-apoc-procedures
https://github.com/neo4j-labs/neosemantics
```
# 127.0.0.1:7474 Import data
```
docker exec -it  neo4j cypher-shell -f /import/cypher.import                       

```

(Good to know) These graph model elements can be overridden by using the following configuration params:

* classLabel: Label to be used for Ontology Classes (categories). Default is Class.
* subClassOfRel: Relationship to be used for rdfs:subClassOf hierarchies. Default is SCO.
* dataTypePropertyLabel: Label to be used for DataType properties in the Ontology. Default is Property.
* objectPropertyLabel: Label to be used for Object properties in the Ontology. Default is Relationship.
* subPropertyOfRel: Relationship to be used for rdfs:subPropertyOf hierarchies. Default is SPO.
* domainRel: Relationship to be used for rdfs:domain. Default is DOMAIN.
* rangeRel: Relationship to be used for rdfs:range. Default is RANGE.

# Make some queries

```
MATCH (n:Class {uri : "https://w3id.org/transmodel/journeys#Line"}) RETURN n LIMIT 25
```

this is the Route Transmodel/gtfs comparison:

```
MATCH (n:Class {name : "Route"})-[]-(p) RETURN n,p LIMIT 25
```

Find the different route types:

```
MATCH (n)-[r:gtfs__routeType]->(o) RETURN distinct o LIMIT 25

MATCH (n:gtfs__Route)-[]-(p) RETURN n,p LIMIT 25
MATCH (n:gtfs__Route)-[r]-(o)  WHERE type(r)  STARTS WITH 'gtfs' RETURN n,o,type(r)   LIMIT 25
```

Find all the stops:
```
MATCH (n:gtfs__Stop)-[r]->(o :Class) RETURN n,type(r),o LIMIT 25

```

# Convert csv gtfs to turtle

```
gtfs-csv2rdf 05-Brest_GTFS.zip http://instant-system.com/core/0.1/ turtle > gtfsintriples.ttl
```

# Import triple to Neo4j

```
call n10s.rdf.import.fetch( "file:///import/data/gtfsintriples.ttl","Turtle");
```

# TODO Reasoning Owl API

https://neo4j.com/blog/using-owl-with-neo4j/                                                                                                              