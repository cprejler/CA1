package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CarFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final CarFacade FACADE =  CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCars(){
        return GSON.toJson(FACADE.getAllCars());
    }
    
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON}) 
    public String getCarCount () {
        long count = FACADE.getCarCount(); 
        
        return "{\"count\":" + count + "}";
    }
    
    @GET
    @Path("/year/{value}")
    @Produces({MediaType.APPLICATION_JSON})
    public String filterByYear(@PathParam("value")int year){
        return GSON.toJson(FACADE.filterByYear(year));
    }
    
    @GET
    @Path("/make/{value}")
    @Produces({MediaType.APPLICATION_JSON})
    public String filterByMake(@PathParam("value")String make){
        return GSON.toJson(FACADE.filterByMake(make));
    }
    @GET
    @Path("/model/{value}")
    @Produces({MediaType.APPLICATION_JSON})
    public String filterByModel(@PathParam("value")String model){
        return GSON.toJson(FACADE.filterByModel(model));
    }
    @GET
    @Path("/price/{value}")
    @Produces({MediaType.APPLICATION_JSON})
    public String filterByPrice(@PathParam("value")Double price){
        return GSON.toJson(FACADE.filterByPrice(price));
    }
    
}
 

