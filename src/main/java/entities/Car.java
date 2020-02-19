/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author casper
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Cars.deleteAllRows", query = "DELETE from Car"),
    @NamedQuery(name = "Cars.getAll", query = "SELECT c from Car c"),
    @NamedQuery(name = "Cars.filterByMake", query = "SELECT c FROM Car c WHERE c.make = :make ORDER BY c.id "),
    @NamedQuery(name = "Cars.filterByYear", query = "SELECT c FROM Car c WHERE c.year = :year ORDER BY c.id"),
    @NamedQuery(name = "Cars.filterByModel", query = "SELECT c FROM Car c WHERE c.model = :model ORDER BY c.id "),
    @NamedQuery(name = "Cars.filterByPrice", query = "SELECT c FROM Car c WHERE c.price = :price ORDER BY c.id "),
})
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String make;
    private String model;
    private double price;

    public Car(int year, String make, String model, double price) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.price = price;
    }
    
    public Car(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

   
    
}
