package com.sinaure.semantic.ontoReasoner;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.rdf4j.RDF4JException;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.query.GraphQueryResult;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class SemanticUtil {

    private static Logger logger = LogManager.getLogger(SemanticUtil.class);

    public static Collection<Statement> readRdfToGraph(final InputStream inputStream,
            final RDFFormat inf, final String baseUrl) {
        try {
            final RDFParser rdfParser = Rio.createParser(inf);
            final StatementCollector collector = new StatementCollector();
            rdfParser.setRDFHandler(collector);
            rdfParser.parse(inputStream, baseUrl);
            return collector.getStatements();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseTurtle(InputStream inputStream, String baseURI, RDFFormat format) {
        try (GraphQueryResult res =
                QueryResults.parseGraphBackground(inputStream, baseURI, format)) {
            while (res.hasNext()) {
                Statement st = res.next();
                logger.info(st.toString());
                // ... do something with the resulting statement here.
            }
        } catch (RDF4JException e) {
            e.printStackTrace();
            // handle unrecoverable error
        }
    }

    public static OWLOntologyManager parseOwl(File f, OWLOntologyManager man) {
        IRI onto = IRI.create(f);
        try {
            OWLOntology o = man.loadOntology(onto);
            for (OWLClass c : getClasses(o)) {
                logger.info("class {}", c.getIRI().toString());
            } ;
            return man;
        } catch (OWLOntologyCreationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static List<OWLClass> getClasses(OWLOntology o) {
        List<OWLClass> classes = new ArrayList<OWLClass>();
        o.classesInSignature().forEach(classes::add);
        return classes;
    }

    public static File getFileFromResources(String fileName) {

        URL url = Application.class.getClassLoader().getResource(fileName);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }

    }
}
