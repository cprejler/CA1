package facades;

import entities.Car;
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
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    public CarFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = CarFacade.getFacadeExample(emf);
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
       facade = CarFacade.getFacadeExample(emf);
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
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.persist(new Car(1997, "Ford", "E350", 3000));
            em.persist(new Car(1999, "Chevy", "Venture", 4900));
            em.persist(new Car(2000, "Chevy", "Venture", 5000));
            em.persist(new Car(1996, "Jeep", "Grand Cherokee", 4799));
            em.persist(new Car(2005, "Volvo", "V70", 44799));
            

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
    public void testGetAllCars(){
        assertEquals(5, facade.getAllCars().size(), "Expects 5 rows in the database");
    }
    
    @Test
    public void testFilterByYear(){
        assertFalse(facade.filterByYear(2000).isEmpty());
        assertEquals(2000, facade.filterByYear(2000).get(0).getYear());
        assertTrue(facade.filterByYear(2000).get(0).getModel().contains("Venture"));
        assertTrue(facade.filterByYear(2000).get(0).getMake().contains("Chevy"));
        assertEquals(5000, facade.filterByYear(2000).get(0).getPrice(), 0.1);

    }
    
    @Test
    public void testFilterByMake(){
        assertFalse(facade.filterByMake("Volvo").isEmpty());
        assertTrue(facade.filterByMake("Volvo").get(0).getMake().contains("Volvo"));
        assertTrue(facade.filterByMake("Volvo").get(0).getModel().contains("V70"));
        assertEquals(2005, facade.filterByMake("Volvo").get(0).getYear());
        assertEquals(44799, facade.filterByMake("Volvo").get(0).getPrice(), 0.1);
        
    }
    @Test
    public void testFilterByModel(){
        assertFalse(facade.filterByModel("Venture").isEmpty());
        assertTrue(facade.filterByModel("Venture").get(0).getModel().contains("Venture"));
        assertTrue(facade.filterByModel("Venture").get(0).getMake().contains("Chevy"));
        assertEquals(2000, facade.filterByModel("Venture").get(0).getYear());
        assertEquals(5000, facade.filterByModel("Venture").get(0).getPrice(), 0.1);
        
    }
    @Test
    public void testFilterByPrice(){
        assertFalse(facade.filterByPrice(4900.0).isEmpty());
        assertEquals(4900, facade.filterByPrice(4900.0).get(0).getPrice(),0.1);
        assertEquals(1999, facade.filterByPrice(4900.0).get(0).getYear());
        assertTrue(facade.filterByPrice(4900.0).get(0).getModel().contains("Venture"));
        assertTrue(facade.filterByPrice(4900.0).get(0).getMake().contains("Chevy"));
    }


}
