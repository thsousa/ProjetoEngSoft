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
public class Mural {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mural;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Condominio condominio;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Aviso> avisos;

    public Mural() {
        this.avisos = new ArrayList<Aviso>();
    }


    public Mural(Condominio condominio, List<Aviso> avisos) {
        super();
        this.condominio = condominio;
        this.avisos = avisos;
    }


    public Mural(Condominio condominio) {
        super();
        this.condominio = condominio;
        this.avisos = new ArrayList<Aviso>();
    }


    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<Aviso> avisos) {
        this.avisos = avisos;
    }

    public void addAviso(Aviso aviso) {
        this.avisos.add(aviso);
    }

    public void removeAviso(Aviso aviso) {
        this.avisos.remove(aviso);
    }

    public int getId_mural() {
        return id_mural;
    }





}
