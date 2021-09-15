package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name= "Employee.deleteAllRows",query = "DELETE from Employee")

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    Integer id;
    String name;
    String address;
    Integer salary;

    public Employee(){};

    public Employee(String name, String address, Integer salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }


}
