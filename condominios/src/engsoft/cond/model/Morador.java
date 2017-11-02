package engsoft.cond.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_morador;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Condominio> condominios;

    @OneToOne
    private Usuario usuario;

    @Basic
    private String bloco;

    @Basic
    private String apartamento;

    public Morador() {
        this.condominios = new ArrayList<Condominio>();
    }

    public Morador(List<Condominio> condominios, Usuario usuario, String bloco,
            String apartamento) {
        super();
        this.condominios = condominios;
        this.usuario = usuario;
        this.bloco = bloco;
        this.apartamento = apartamento;
    }

    public Morador(Usuario usuario, String bloco, String apartamento) {
        super();
        this.condominios = new ArrayList<Condominio>();
        this.usuario = usuario;
        this.bloco = bloco;
        this.apartamento = apartamento;
    }

    public List<Condominio> getCondominios() {
        return condominios;
    }

    public void setCondominios(List<Condominio> condominios) {
        this.condominios = condominios;
    }

    public void addCondominio(Condominio condominio) {
        this.condominios.add(condominio);
    }

    public void removeCondominio(Condominio condominio) {
        this.condominios.remove(condominio);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public int getId_morador() {
        return id_morador;
    }

}
