package facades;

import com.google.gson.Gson;
import dto.MemberDTO;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MemberFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Member> getAllMembers(){
        EntityManager em = getEntityManager();
        
      //  Query query = em.createNamedQuery("Members.getAll");
        //List<Member> members = query.getResultList();
      //  System.out.println("dsafasfsa"+members.size());
        
        try {
            TypedQuery<Member> query = em.createQuery("Select m from Member m", Member.class); 
            System.out.println(query.getResultList().size());
            //return new Gson().toJson(query.getResultList()); 
            return query.getResultList(); 
        }
        finally {
            em.close();
        }
    }
    
    public long getMembersCount () {
        EntityManager em = emf.createEntityManager(); 
        try {
            long membercount = (long) em.createQuery("SELECT COUNT(r) FROM Member r").getSingleResult(); 
            return membercount; 
        }
        finally {
            em.close();
        }
    }
        
        
        
       // return members;

    public List<Member> findByID(int id) {
        EntityManager em = emf.createEntityManager(); 
        try {
            TypedQuery<Member> query = em.createQuery("Select m from Member m where m.id = :id", Member.class);
            query.setParameter("id", id); 
            return query.getResultList(); 
        }
        finally {
            em.close();
        }
    }
        
        
    }
    
        
    
    

