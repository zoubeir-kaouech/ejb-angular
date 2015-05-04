package services;

import domain.Todo;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ego on 05/04/15.
 */
@Stateless
public class TodoService {

    @PersistenceContext
    private EntityManager em;

    public TodoService(){
    }

    public Todo create(Todo todo){
        em.persist(todo);
        return todo;
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Todo find(Integer id){
        return em.find(Todo.class, id);
    }

    public Todo update(Todo todo){
        return em.merge(todo);
    }

    public void delete(Integer id){
        em.remove(em.find(Todo.class, id));
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public List<Todo> findAll(){
        return em.createQuery("from Todo", Todo.class).getResultList();
    }
}
