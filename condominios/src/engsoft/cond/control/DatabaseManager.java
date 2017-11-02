package engsoft.cond.control;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import engsoft.cond.model.Usuario;
import engsoft.cond.persistence.EMF;

public class DatabaseManager {

    private static DatabaseManager autoRef;
    private static final Log LOGGER = LogFactory.getLog(DatabaseManager.class);

    @PersistenceUnit
    protected EntityManager em;

    public DatabaseManager() {
        em = EMF.get().createEntityManager();
    }

    public static DatabaseManager getInstance() {
        if (autoRef == null) {
            autoRef = new DatabaseManager();
        }

        return autoRef;
    }
    
    @SuppressWarnings("rawtypes")
    public ArrayList<Object> getListFromTable(String table) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query q = em.createQuery("SELECT p FROM " + table +" p");
            java.util.List results = q.getResultList();
            Iterator iter = results.iterator();
            ArrayList<Object> objs = new ArrayList<Object>();
            while (iter.hasNext()) {                
                Object obj = iter.next();
                if (obj != null) {
                    objs.add(obj);
                }
            }
            tx.commit();
            return(objs);
        } catch (Exception e) {

            LOGGER.error("Error reading objects.");
            e.printStackTrace();
            return(null);

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }

    /**
     * Persite o usuário no banco de dados. Checagem de CPF/CNPJ/e-mail/telefone
     * válidos pode (deveria) ser feita aqui por ser "server-side"
     * 
     * @TODO melhorar tratamento de erros
     * 
     * @param user
     */
    public boolean saveUsuario(Usuario user) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            em.flush();
            em.refresh(user);

            tx.commit();
            return(true);
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar usuário.");
            e.printStackTrace();
            return(false);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }
    
    
    /**
     * Persite uma entidade genérica no banco de dados
     *  
     * @param condominio
     */
    public boolean saveEntity(Object entity) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            em.flush();
            em.refresh(entity);

            tx.commit();
            return(true);
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar objeto.");
            e.printStackTrace();
            return(false);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }
    
    
    public void clearTable(String table) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Query q = em.createQuery("DELETE FROM " + table + " p");
        q.executeUpdate();
        tx.commit();
        
        if (tx.isActive()) {
            tx.rollback();
        }

        em.close();        
    }
    
    public Usuario getRegisteredUser(String email) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query q = em.createQuery("SELECT p FROM Usuario p WHERE p.email = '" + email + "'");
            Usuario us = null;
            
            try {
                us = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
                us = null;
            }
            tx.commit();
            return(us);
        } catch (Exception e) {

            LOGGER.error("Error reading objects.");
            e.printStackTrace();
            return(null);

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
        
    }

}
