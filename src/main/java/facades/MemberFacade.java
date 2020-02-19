package facades;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.MemberDTO;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.modelmapper.ModelMapper;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

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

    public List<MemberDTO> getAllMembers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Member> query = em.createQuery("Select m from Member m", Member.class);
            List<Member> members = query.getResultList();
            java.lang.reflect.Type listType = new TypeToken<List<MemberDTO>>() {
            }.getType();
            List<MemberDTO> returnValue = new ModelMapper().map(members, listType);
            return returnValue;

            //return new Gson().toJson(query.getResultList()); 
            //   return query.getResultList(); 
        } finally {
            em.close();
        }
    }

    public long getMembersCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long membercount = (long) em.createQuery("SELECT COUNT(r) FROM Member r").getSingleResult();
            return membercount;
        } finally {
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
        } finally {
            em.close();
        }
    }

}
