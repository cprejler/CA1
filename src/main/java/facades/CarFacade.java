/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author casper
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    private CarFacade() {
    }

    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Car> getAllCars() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Car> cars = em.createQuery("SELECT c FROM Car c", Car.class);
            return cars.getResultList();
        }finally{
            em.close();;
        }

    }
    public Long getCarCount(){
        EntityManager em = getEntityManager();
        try{
            Long carCount = (Long)em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            //Query carCount = em.createQuery("SELECT COUNT(c) FROM Car c");
            return carCount;
        }finally{
            em.close();
        }
    }
    
    

}
