package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MemberDTO;
import entities.Member;
import utils.EMF_Creator;
import facades.MemberFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MemberFacade FACADE =  MemberFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers(){
        return GSON.toJson(FACADE.getAllMembers()); 
    }
    
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getMembersCount () {
        long count = FACADE.getMembersCount(); 
        
        return "{\"count\":" + count + "}";
    }
    
    @GET
    @Path("members/{id}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getMemberById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.findByID(id)); 
    }
    
}
 

