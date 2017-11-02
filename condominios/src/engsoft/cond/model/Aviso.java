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
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_aviso;
    
    @OneToOne
    private Usuario usuario;    

    @OneToMany(fetch=FetchType.EAGER)
    private List<ComentarioAviso> comentarios;
    
    @Basic 
    private String datahora;

    @Basic 
    private String titulo; 
    
    @Basic
    private String texto;
    
    public Aviso() {
        this.comentarios = new ArrayList<ComentarioAviso>();        
    }
    
    

    public Aviso(Usuario usuario, String datahora, String titulo, String texto) {
        super();
        this.usuario = usuario;
        this.datahora = datahora;
        this.titulo = titulo;
        this.texto = texto;
        this.comentarios = new ArrayList<ComentarioAviso>();
    }


    

    public List<ComentarioAviso> getComentarios() {
        return comentarios;
    }



    public void setComentarios(List<ComentarioAviso> comentarios) {
        this.comentarios = comentarios;
    }
    
    public void addComentario(ComentarioAviso comentario) {
        this.comentarios.add(comentario);
    }

    public void removeComentario(ComentarioAviso comentario) {
        this.comentarios.remove(comentario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId_aviso() {
        return id_aviso;
    }
    
    
}
