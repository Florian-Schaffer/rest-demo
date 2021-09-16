package facades;

import dtos.DTOMovie;
import entities.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieFacade {
    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    private MovieFacade(){

    }

    public static MovieFacade movieFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    public static DTOMovie getMovieById(Integer id) throws Exception{
        EntityManager em = emf.createEntityManager();

        Movie movie = em.find(Movie.class,id);
        em.close();
        if(movie!=null){
            return new DTOMovie(movie);
        }else{
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public List<DTOMovie> getMoviesByName(String title) throws Exception{
        EntityManager em = emf.createEntityManager();
            try{
                em.getTransaction().begin();
                TypedQuery<Movie> typedQuery = em.createQuery("SELECT m FROM Movie m WHERE m.title=:title",Movie.class);
                typedQuery.setParameter("title",title);
                List<Movie> result = typedQuery.getResultList();
                em.getTransaction().commit();
                return (List<DTOMovie>) (List<?>) result;
            } finally {
                emf.close();
                em.close();

            }
    }

    @SuppressWarnings("unchecked")
    public List<DTOMovie> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Movie> typedQuery = em.createQuery("SELECT m FROM Movie m",Movie.class);
            List<Movie> result = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<DTOMovie>) (List<?>) result;
        } finally{
            emf.close();
            em.close();
        }
    }


    @SuppressWarnings("unchecked")
    public List<DTOMovie> getMoviesByReleaseYear(int year){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            TypedQuery<Movie> typedQuery = em.createQuery("SELECT m FROM Movie m WHERE m.year=:year",Movie.class);
            List<Movie> result = typedQuery.getResultList();
            em.getTransaction().commit();
            return (List<DTOMovie>) (List<?>) result;
        }finally{
            emf.close();
            em.close();
        }

    }

    public void createMovie(DTOMovie DTOmovie){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new Movie());
        }finally{
            emf.close();
            em.close();
        }
    }




}
