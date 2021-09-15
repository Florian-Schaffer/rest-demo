package facades;

import dtos.DTOEmployee;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeFacade {

private static EmployeeFacade instance;
private static EntityManagerFactory emf;

    private EmployeeFacade(){

    }

    public static EmployeeFacade employeeFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }


    public static DTOEmployee getEmployeeById(Integer id) throws Exception{

        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class,id);
        em.close();
        if(employee!= null){
            return new DTOEmployee(employee);
        }else{
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public List<DTOEmployee> getEmployeesByName(String name) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Employee> typedQuery = em.createQuery("select e from Employee e where e.name=:name",Employee.class);
            typedQuery.setParameter("name",name);
            List<Employee> result = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<DTOEmployee>) (List<?>) result;
        }finally {
            emf.close();
            em.close();
        }

    }

    @SuppressWarnings("unchecked")
    public List<DTOEmployee> getAllEmployees(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Employee> typedQuery = em.createQuery("SELECT e FROM Employee e",Employee.class);
            List<Employee> result = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<DTOEmployee>) (List<?>) result;
        }finally{
            emf.close();
            em.close();
        }
    }


    @SuppressWarnings("unchecked")
    public List<DTOEmployee> getEmployeesWithHighSalary(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Employee> typedQuery = em.createQuery("SELECT e FROM Employee e where e.salary>10000",Employee.class);
            List<Employee> result = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<DTOEmployee>) (List<?>) result;
        } finally {
            emf.close();
            em.close();
        }

    }


    public void createEmployee(DTOEmployee DTOemployee){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new Employee());

        }finally{
            emf.close();
            em.close();
        }

    }




}
