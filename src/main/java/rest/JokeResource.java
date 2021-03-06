package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.JokeFacade;
import java.util.ArrayList;
import java.util.Random;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final JokeFacade FACADE =  JokeFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes(){
        return GSON.toJson(FACADE.getAllJokes());
    }
    
    @GET
    @Path("jokes/{id}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getJokeById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.findByID(id)); 
    }
    
    @GET
    @Path("/random")
    @Produces
    public String methodName(){
        return new Gson().toJson(FACADE.getRandomJoke());

    }
}
 

