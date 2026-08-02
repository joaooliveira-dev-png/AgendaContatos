package br.com.joaovitor.agenda.dao;

import br.com.joaovitor.agenda.model.Contato;
import br.com.joaovitor.agenda.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class ContatoDAO {
    
    
    public void salvar(Contato c){
        EntityTransaction transaction = null;
        
        try(EntityManager em = JPAUtil.getEntityManager()){
            transaction = em.getTransaction();
             
            transaction.begin();
            em.persist(c);
            transaction.commit();
            
        }catch(Exception erro){
           
           if(transaction != null && transaction.isActive()){
               transaction.rollback();
           } 
           
           erro.printStackTrace();
        }
    }
    
    public List<Contato> listar(){
        try(EntityManager em = JPAUtil.getEntityManager()){
        
        List<Contato> lista = em.createQuery("SELECT c FROM Contato c", 
                Contato.class).getResultList();
        
        return lista;
        }
    }
    
    
}
