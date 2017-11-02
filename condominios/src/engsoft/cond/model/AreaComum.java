package engsoft.cond.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AreaComum {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_area;
        
    @Basic 
    private String nome;

    @Basic 
    private String descricao;
    
    public AreaComum() {
        
    }    
    

    public AreaComum(String nome, String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_area() {
        return id_area;
    } 
    
    @Override
    public String toString() {
        return "Área " + nome + " - " + descricao;
    }
    
    

}
