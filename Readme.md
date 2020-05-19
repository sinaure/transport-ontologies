# download jars and add to plugin folder
```
https://github.com/neo4j-contrib/neo4j-apoc-procedures
https://github.com/neo4j-labs/neosemantics
```
# 127.0.0.1:7474 Import data
```
docker exec -it  neo4j cypher-shell -f /import/cypher.import                       

```
# Make some query
```
MATCH (n:Class {uri : "https://w3id.org/transmodel/journeys#Line"}) RETURN n LIMIT 25
MATCH (n:Class {uri : "https://w3id.org/transmodel/journeys#Line"})-[r:DOMAIN]-(o) RETURN n,o LIMIT 25
```

# TODO Reasoning Owl API
https://neo4j.com/blog/using-owl-with-neo4j/                                                                                                              