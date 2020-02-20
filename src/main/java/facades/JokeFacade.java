/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;
import com.google.gson.Gson;
import dto.JokeDTO;
import dto.MemberDTO;
import entities.Joke;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author jenso
 */
public class JokeFacade {
    
    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }
    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public static List<JokeDTO>  getAllJokes(){
        EntityManager em = getEntityManager();
        
              try {
            TypedQuery<Joke> query = em.createQuery("Select j from Joke j", Joke.class); 
            System.out.println(query.getResultList().size());
            //return new Gson().toJson(query.getResultList());
            List<Joke> resultList = query.getResultList(); 
            List<JokeDTO> DTOResult = new ArrayList();
        for (Joke joke : resultList) {
            DTOResult.add(new JokeDTO(joke));
        }
            return DTOResult; 
              }
        finally {
            em.close();
        }
    }
    public List<Joke> findByID(int id) {
        EntityManager em = emf.createEntityManager(); 
        try {
            TypedQuery<Joke> query = em.createQuery("Select j from Joke j where j.id = :id", Joke.class);
            query.setParameter("id", id); 
            return query.getResultList(); 
        }
        finally {
            em.close();
        }
    }
    public List<Joke> getRandomJoke() {
        EntityManager em = emf.createEntityManager(); 
        try {
            Random rand = new Random();
            int jokeCount = (int) getJokesCount();
            int id = rand.nextInt(jokeCount) + 1;
            TypedQuery<Joke> query = em.createQuery("Select j from Joke j where j.id = :id", Joke.class);
            query.setParameter("id", id); 
            return query.getResultList(); 
        }
        finally {
            em.close();
        }
    }
    public static long getJokesCount () {
        EntityManager em = emf.createEntityManager(); 
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(r) FROM Joke r").getSingleResult(); 
            return jokeCount; 
        }
        finally {
            em.close();
        }
    }
}
