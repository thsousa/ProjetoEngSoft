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

import engsoft.cond.model.AreaComum;
import engsoft.cond.model.Condominio;
import engsoft.cond.model.Usuario;
import engsoft.cond.persistence.EMF;

public class DatabaseManager {

    private static DatabaseManager autoRef;
    private static final Log LOGGER = LogFactory.getLog(DatabaseManager.class);
    private static final String[] TABLES = {"Usuario", "Condominio", "Mural", "Calendario",
    									    "Aviso", "Reserva", "ComentarioAviso", "ComentarioReserva",
    									    "AreaComum"};

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

    public void clearAllTables() {
    	for (String t : TABLES) {
    		clearTable(t);
    	}
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

    // TODO se for facilitar, colocar alguns avisos/reservas tambem
    public void populate() {

    	Usuario u1 = new Usuario("morador1@t.com", "Morador 1", "123", "", "", "", "m", "123", "A");
    	Usuario u2 = new Usuario("morador2@t.com", "Morador 2", "124", "", "", "", "m", "124", "A");
    	Usuario u3 = new Usuario("funcionario1@t.com", "Funcionario 1", "125", "", "", "", "f", "", "");
    	Usuario u4 = new Usuario("funcionaromorador1@t.com", "FuncMora 1", "126", "", "", "", "fm", "", "");
    	Usuario u5 = new Usuario("sindico1@t.com", "Sindico 1", "127", "", "", "", "s", "", "");
    	Usuario u6 = new Usuario("sindicomorador1@t.com", "SindMora 1", "128", "", "", "", "sm", "", "");
    	Usuario u7 = new Usuario("adm1@t.com", "Administradora 1", "", "129", "", "", "a", "", "");
    	Usuario u8 = new Usuario("sysadm1@t.com", "Sysadm 1", "1", "", "", "", "x", "", "");

    	Usuario u9 = new Usuario("morador3@t.com", "Morador 3", "223", "", "", "", "m", "55", "B");
    	Usuario u10 = new Usuario("morador4@t.com", "Morador 4", "224", "", "", "", "m", "12", "B");
    	Usuario u11 = new Usuario("funcionario2@t.com", "Funcionario 2", "225", "", "", "", "f", "", "");
    	Usuario u12 = new Usuario("sindico2@t.com", "Sindico 2", "227", "", "", "", "s", "", "");
    	Usuario u13 = new Usuario("adm2@t.com", "Administradora 2", "", "229", "", "", "a", "", "");

    	Usuario u14 = new Usuario("usuario@t.com", "Usuario 1", "000", "", "", "", "m", "", "");

    	AreaComum a1 = new AreaComum("Churrasqueira", "Churrasqueira, capacidade 30 pessoas.");
    	AreaComum a2 = new AreaComum("Salão de festas bloco A", "Salão de festas do bloco A, capacidade 100 pessoas.");
    	AreaComum a3 = new AreaComum("Salão de festas bloco B", "Salão de festas do bloco B, capacidade 50 pessoas.");
    	AreaComum a4 = new AreaComum("Academia", "Academia com esteiras e equipamentos de musculação.");

    	Condominio c1 = new Condominio("Cond1", "local 1");
    	Condominio c2 = new Condominio("Cond2", "local 2");

    	c1.addArea(a1);
    	c1.addArea(a2);
    	c1.addArea(a3);

    	c2.addArea(a4);

    	u1.addCondominio(c1);
    	u2.addCondominio(c1);
    	u3.addCondominio(c1);
    	u4.addCondominio(c1);
    	u5.addCondominio(c1);
    	u6.addCondominio(c1);
    	u7.addCondominio(c1);

    	u9.addCondominio(c2);
    	u10.addCondominio(c2);
    	u11.addCondominio(c2);
    	u12.addCondominio(c2);
    	u13.addCondominio(c2);
    	u7.addCondominio(c2);


    	saveEntity(a1);
    	saveEntity(a2);
    	saveEntity(a3);
    	saveEntity(a4);

    	saveEntity(c1);
    	saveEntity(c2);

    	saveEntity(u1);
    	saveEntity(u2);
    	saveEntity(u3);
    	saveEntity(u4);
    	saveEntity(u5);
    	saveEntity(u6);
    	saveEntity(u7);
    	saveEntity(u8);
    	saveEntity(u9);
    	saveEntity(u10);
    	saveEntity(u11);
    	saveEntity(u12);
    	saveEntity(u13);
    	saveEntity(u14);

    }

}
