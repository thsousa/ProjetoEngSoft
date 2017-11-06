package engsoft.cond.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_condominio;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<AreaComum> areas;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Mural mural;

    @Basic
    private String nome;

    @Basic
    private String endereco;

    public Condominio() {
        this.areas = new ArrayList<AreaComum>();
    }


    public Condominio(String nome, String endereco) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.areas = new ArrayList<AreaComum>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId_condominio() {
        return id_condominio;
    }


    public List<AreaComum> getAreas() {
        return areas;
    }


    public void setAreas(List<AreaComum> areas) {
        this.areas = areas;
    }

    public void addArea(AreaComum area) {
        this.areas.add(area);
    }

    public void removeArea(AreaComum area) {
        this.areas.remove(area);
    }


    public Mural getMural() {
        return mural;
    }


    public void setMural(Mural mural) {
        this.mural = mural;
    }


    @Override
    public String toString() {
        String str_areas = "Areas: ";
        for(AreaComum a : areas) {
            str_areas = str_areas + "\n" + a.toString();
        }
        return "Condom�nio " + nome + " - endere�o: " + endereco + "\n" + str_areas;
    }




}
