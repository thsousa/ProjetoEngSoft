package engsoft.cond.control;

import java.util.ArrayList;

import engsoft.cond.model.AreaComum;
import engsoft.cond.model.Condominio;
import engsoft.cond.screen.ReservationsScreen;

public class ReservationsManager {

    private static ReservationsManager autoRef;


    private ReservationsScreen reservationsScreen;


    public ReservationsManager() {

    }

    public static ReservationsManager getInstance() {
        if (autoRef == null) {
            autoRef = new ReservationsManager();
        }

        return autoRef;
    }


    public ReservationsScreen getReservationsScreen(Condominio cond) {
        reservationsScreen = new ReservationsScreen(new ArrayList<AreaComum>(cond.getAreas()));
        return reservationsScreen;
    }

}
