package engsoft.cond.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_calendario;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Condominio condominio;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reserva> reservas;


    public Calendario() {
        this.reservas = new ArrayList<Reserva>();

    }

    public Calendario(Condominio condominio) {
        this.condominio = condominio;
        this.reservas = new ArrayList<Reserva>();
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public void removeReserva(Reserva reserva) {
        this.reservas.remove(reserva);
    }

    public int getId_calendario() {
        return id_calendario;
    }




}
