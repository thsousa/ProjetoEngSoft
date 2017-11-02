package engsoft.cond.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Cria {@link EntityManagerFactory} atrelado ao bd do projeto.
 * 
 * @author Ogawa
 *
 */
public final class EMF {

    private static final EntityManagerFactory emfInstance = Persistence
            .createEntityManagerFactory("condDB");

    private EMF() {
    }

    public static EntityManagerFactory get() {
        return emfInstance;
    }

}
