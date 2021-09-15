package facades;

import dtos.DTOEmployee;
import entities.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeFacadeTest {
    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;
    private static Employee e1, e2, e3, e4;

    public EmployeeFacadeTest(){

    }

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.employeeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass(){

    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            e1 = new Employee("Kurt", "Københavngade 123",50000);
            e2 = new Employee("Mig","Hvor jeg bor",10000);
            e3 = new Employee("Dig", "Hvor du bor",15371);
            e4 = new Employee("Farvel", "Tak for i dag",1138);
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
            em.persist(e4);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Test
    public void getEmployeeByID(Integer id)throws Exception{
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
           DTOEmployee result = EmployeeFacade.getEmployeeById(e1.getId());

            assertEquals(result.getName(),e1.getName());
            em.getTransaction().commit();

        }finally{
            em.close();

        }
    }

    @Test
    public void getEmployeesByName(String name) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Employee> typedQuery = em.createQuery("SELECT e FROM Employee e WHERE e.name=:name",Employee.class);
            typedQuery.setParameter("name",name);
            List<Employee> result = typedQuery.getResultList();


        }finally{
            emf.close();
            em.close();

        }
    }



}
