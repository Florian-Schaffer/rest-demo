package facades;

import entities.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MovieFacadeTest {
    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie m1, m2, m3, m4;

    public MovieFacadeTest(){

    }

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.movieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass(){

    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            m1 = new Movie(1999,"Star Wars", new String[]{"Mig", "Dig", "Ham"});
            m2 = new Movie();
            m3 = new Movie();
            m4 = new Movie();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }




}
