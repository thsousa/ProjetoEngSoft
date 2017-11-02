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
public class Administradora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_administradora;
    
    @OneToMany(fetch=FetchType.EAGER)
    private List<Condominio> condominios;
    
    @OneToOne
    private Usuario usuario;
    
    @Basic 
    private String endereco;    
    

    public Administradora() {
        this.condominios = new ArrayList<Condominio>();        
    }
    
    public Administradora(List<Condominio> condominios, Usuario usuario,
            String endereco) {
        super();
        this.condominios = condominios;
        this.usuario = usuario;
        this.endereco = endereco;
    }
    
    public Administradora(Usuario usuario,
            String endereco) {
        super();
        this.condominios = new ArrayList<Condominio>();
        this.usuario = usuario;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId_administradora() {
        return id_administradora;
    }
    
    

}
