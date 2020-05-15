package ontoReasoner;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sinaure.semantic.ontoReasoner.Application;
import com.sinaure.semantic.ontoReasoner.GraphdbService;
import com.sinaure.semantic.ontoReasoner.SemanticUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InsertOwlTest {
    @Autowired
    private GraphdbService graphdbService;

    @Autowired
    private OWLOntologyManager owlOntologyManager;

    private static String OWL = "ontologies/transmodel/tm-commons.owl";

    @Test
    public void insertOwlTest() {
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        graphdbService.importOwl(SemanticUtil.getFileFromResources(OWL), man);
    }

    @Test
    public void owlOntologyManagerTest() {
        assertThat(owlOntologyManager.getOntologies()).hasSize(2);;
    }


}
