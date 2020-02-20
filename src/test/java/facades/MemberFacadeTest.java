package facades;

import dto.MemberDTO;
import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MemberFacade.getFacadeExample(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MemberFacade.getFacadeExample(emf);
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
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(new Member("Casper", "cph-cp277", "cprejler", "prejler93@gmail.com"));
            em.persist(new Member("Jens", "cph-jb361", "JOhlendorff", "jens.ohlendorff@gmail.com"));
            em.persist(new Member("Aske", "cph-at191", "atthorsen", "thorsen.aske@gmail.com"));
            em.persist(new Member("Mikkel", "cph-ml596", "Malthorn1", "mikkel@punani.com"));
            

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
    public void testGetAllMembers(){
        assertEquals(4, facade.getAllMembers().size(), "Expects 4 rows in the database");
        
        
    }
    
    
    //@Test
    public void testFindByID(){
        List<Member> member = facade.findByID(0);
        String name = member.get(0).getName();
        String github = member.get(0).getGithub();
        String email = member.get(0).getEmail();
        String studentID = member.get(0).getStudentID();
        
        assertTrue(facade.findByID(1).get(0).getName().contains(name));
        assertTrue(facade.findByID(1).get(0).getGithub().contains(github));
        assertTrue(facade.findByID(1).get(0).getEmail().contains(email));
        assertTrue(facade.findByID(1).get(0).getStudentID().contains(studentID));
    }
    

}
