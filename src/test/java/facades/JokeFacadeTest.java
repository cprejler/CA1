package facades;

import entities.Joke;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    public JokeFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = JokeFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = JokeFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(new Joke("Din mor er så tyk at hun har sit eget kredsløb", "Din mor", "jens"));
            em.persist(new Joke("Knock knock, whos there? Interrupting cow, interrupting cow wh- moo", "Banke banke på", "jens"));
            em.persist(new Joke("Hvorfor gik kyllingen over vejen? For at komme til den anden side", "Kylling", "jens"));
            em.persist(new Joke("Hvor mange babyer tager det at male et hus? Det kommer an på hvor hårdt man kaster dem", "død baby", "wikipedia"));
            em.persist(new Joke("Hvad ser du hvis du trækker ærmerne op på en lynkineser? Man ser hans arme", "Ikke sjov", "jens"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllJokes() {
        assertEquals(5, facade.getAllJokes().size(), "Expects 5 rows in the database");
    }


}
