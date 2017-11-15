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
import engsoft.cond.model.Aviso;
import engsoft.cond.model.Calendario;
import engsoft.cond.model.ComentarioAviso;

import static engsoft.cond.util.Constants.*;
import engsoft.cond.model.Condominio;
import engsoft.cond.model.Mural;
import engsoft.cond.model.Reserva;
import engsoft.cond.model.Usuario;
import engsoft.cond.persistence.EMF;

public class DatabaseManager {

    private static DatabaseManager autoRef;
    private static final Log LOGGER = LogFactory.getLog(DatabaseManager.class);
    private static final String[] TABLES = { "Usuario", "Condominio", "Mural",
            "Calendario", "Aviso", "Reserva", "ComentarioAviso", "ComentarioReserva",
            "AreaComum" };

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
            Query q = em.createQuery("SELECT p FROM " + table + " p");
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
            return (objs);
        } catch (Exception e) {

            LOGGER.error("Error reading objects.");
            e.printStackTrace();
            return (null);

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }

    /**
     * Persite o usuÃ¡rio no banco de dados. Checagem de
     * CPF/CNPJ/e-mail/telefone vÃ¡lidos pode (deveria) ser feita aqui por ser
     * "server-side"
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
            return (true);
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar usuÃ¡rio.");
            e.printStackTrace();
            return (false);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }

    /**
     * Persite uma entidade genÃ©rica no banco de dados
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
            return (true);
        } catch (Exception e) {
            LOGGER.error("Erro ao salvar objeto.");
            e.printStackTrace();
            return (false);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }

    public boolean removeEntity(Object entity) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(entity);
            tx.commit();
            return (true);
        } catch (Exception e) {
            LOGGER.error("Erro ao remover objeto.");
            e.printStackTrace();
            return (false);
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

    public void updateUser(Usuario u) {
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Usuario curr = em.find(Usuario.class, u.getId_usuario());

        try {
            tx.begin();

            // TODO adicionar outros campos
            // TODO handle e-mail change properly

            if (!(curr.getCondominios().equals(u.getCondominios()))) {
                curr.setCondominios(u.getCondominios());
            }

            if (!(curr.getNivel_acesso().equals(u.getNivel_acesso()))) {
                curr.setNivel_acesso(u.getNivel_acesso());
            }

            if (!(curr.getEmail().equals(u.getEmail()))) {
                curr.setEmail(u.getEmail());
            }

            if (!(curr.getTel1().equals(u.getTel1()))) {
                curr.setTel1(u.getTel1());
            }

            if (!(curr.getTel2().equals(u.getTel2()))) {
                curr.setTel2(u.getTel2());
            }

            if (!(curr.getNome().equals(u.getNome()))) {
                curr.setNome(u.getNome());
            }

            tx.commit();
        } catch (Exception e) {

            LOGGER.error("Error updating user.");
            e.printStackTrace();

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }
    }

    public Usuario getRegisteredUser(String email) {

        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query q = em.createQuery(
                    "SELECT p FROM Usuario p WHERE p.email = '" + email + "'");
            Usuario us = null;

            try {
                us = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
                us = null;
            }
            tx.commit();
            return (us);
        } catch (Exception e) {

            LOGGER.error("Error reading objects.");
            e.printStackTrace();
            return (null);

        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }

            em.close();
        }

    }
    
    public void removeUserFromBuilding(Usuario u, Condominio bldg) {
        // TODO arrumar remoção, não parece estar removendo de verdade no bd
        em = EMF.get().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        u.removeCondominio(bldg);
        tx.commit();
        em.close();
    }

    public void removeBuilding(Condominio bldg) {
        // TODO verificar remoção
        em = EMF.get().createEntityManager();
        EntityTransaction tx;
        ArrayList<Object> users = getListFromTable("Usuario");

        for (Object u : users) {
            Usuario us = (Usuario) u;

            if (us.getCondominios().contains(bldg)) {
                tx = em.getTransaction();
                tx.begin();
                us.removeCondominio(bldg);
                tx.commit();
            }
        }

        ArrayList<Object> cals = getListFromTable("Calendario");

        for (Object u : cals) {
            Calendario us = (Calendario) u;

            if (us.getCondominio().equals(bldg)) {
                tx = em.getTransaction();
                tx.begin();
                us.setCondominio(null);
                tx.commit();
            }
        }

        ArrayList<Object> mur = getListFromTable("Mural");

        for (Object u : mur) {
            Mural us = (Mural) u;

            if (us.getCondominio().equals(bldg)) {
                tx = em.getTransaction();
                tx.begin();
                us.setCondominio(null);
                tx.commit();
            }
        }
        em.close();
        removeEntity(bldg);
    }

    // TODO se for facilitar, colocar alguns avisos/reservas tambem
    public void populate() {

        Usuario u1 = new Usuario("morador1@t.com", "Morador 1", "123", "", "", "",
                "m", "123", "A");
        Usuario u2 = new Usuario("morador2@t.com", "Morador 2", "124", "", "", "",
                "m", "124", "A");
        Usuario u3 = new Usuario("funcionario1@t.com", "Funcionario 1", "125", "",
                "", "", "f", "", "");
        Usuario u4 = new Usuario("funcionaromorador1@t.com", "FuncMora 1", "126", "",
                "", "", "fm", "", "");
        Usuario u5 = new Usuario("sindico1@t.com", "Sindico 1", "127", "", "", "",
                "s", "", "");
        Usuario u6 = new Usuario("sindicomorador1@t.com", "SindMora 1", "128", "",
                "", "", "sm", "", "");
        Usuario u7 = new Usuario("adm1@t.com", "Administradora 1", "", "129", "", "",
                "a", "", "");
        Usuario u8 = new Usuario("sysadm1@t.com", "Sysadm 1", "1", "", "", "", "x",
                "", "");

        Usuario u9 = new Usuario("morador3@t.com", "Morador 3", "223", "", "", "",
                "m", "55", "B");
        Usuario u10 = new Usuario("morador4@t.com", "Morador 4", "224", "", "", "",
                "m", "12", "B");
        Usuario u11 = new Usuario("funcionario2@t.com", "Funcionario 2", "225", "",
                "", "", "f", "", "");
        Usuario u12 = new Usuario("sindico2@t.com", "Sindico 2", "227", "", "", "",
                "s", "", "");
        Usuario u13 = new Usuario("adm2@t.com", "Administradora 2", "", "229", "",
                "", "a", "", "");

        Usuario u14 = new Usuario("usuario@t.com", "Usuario 1", "000", "", "", "",
                "m", "", "");

        AreaComum a1 = new AreaComum("Churrasqueira",
                "Churrasqueira, capacidade 30 pessoas.");
        AreaComum a2 = new AreaComum("Salão de festas bloco A",
                "Salão de festas do bloco A, capacidade 100 pessoas.");
        AreaComum a3 = new AreaComum("Salão de festas bloco B",
                "Salão de festas do bloco B, capacidade 50 pessoas.");
        AreaComum a4 = new AreaComum("Academia",
                "Academia com esteiras e equipamentos de musculação.");

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
        
        Reserva r1 = new Reserva(u1, a1, "30/11/2017", "15:50-20:50", null, RESERVA_APROVADA);
        Reserva r2 = new Reserva(u2, a1, "30/11/2017", "15:00-20:00", null, RESERVA_REJEITADA);
        Reserva r3 = new Reserva(u1, a2, "30/12/2017", "12:00-00:00", null, RESERVA_PENDENTE);
        Reserva r4 = new Reserva(u1, a1, "30/12/2017", "12:00-00:00", null, RESERVA_PENDENTE);
        Reserva r5 = new Reserva(u2, a2, "30/12/2017", "12:00-00:00", null, RESERVA_PENDENTE);
        
        Calendario cal1 = new Calendario(c1);
        cal1.addReserva(r1);
        cal1.addReserva(r2);
        cal1.addReserva(r3);
        cal1.addReserva(r4);
        cal1.addReserva(r5);

        
        saveEntity(r1);
        saveEntity(r2);
        saveEntity(r3);
        saveEntity(r4);
        saveEntity(r5);
        
        saveEntity(cal1);
        
        
        Aviso av1 = new Aviso(u1, "11/15/2017 17:50", "VENDA - Bicicleta", "Vendo bicicleta Caloi, contato xxxx-xxxx");
        Aviso av2 = new Aviso(u5, "11/10/2017 08:00", "Festa de fim de ano", "A festa de fim de ano está marcada para o dia 03/12/2017, a partir das 11hrs");

        ComentarioAviso cav1 = new ComentarioAviso(u2, "11/15/2017 17:52", "Liguei mas ninguém atendeu");
        ComentarioAviso cav2 = new ComentarioAviso(u3, "11/14/2017 14:22", "Legal!");
        
        av1.addComentario(cav1);
        av2.addComentario(cav2);
        
        Mural mur1 = new Mural(c1);
        mur1.addAviso(av1);
        mur1.addAviso(av2);
        
        saveEntity(cav1);
        saveEntity(cav2);
        saveEntity(av1);
        saveEntity(av2);
        saveEntity(mur1);
        
        
    }

}
