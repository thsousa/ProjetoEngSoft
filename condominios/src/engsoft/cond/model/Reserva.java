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
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reserva;
    
    @OneToOne
    private Usuario usuario;    

    @OneToOne
    private AreaComum area;    


    @OneToMany(fetch=FetchType.EAGER)
    private List<ComentarioReserva> comentarios;
    
    @Basic 
    private String data;

    @Basic 
    private String hora;

    @Basic 
    private String nome_morador; 
    
    @Basic
    private int status;
    
    public Reserva() {
        this.comentarios = new ArrayList<ComentarioReserva>();        
    }   
    

    public Reserva(Usuario usuario, AreaComum area, String data, String hora,
            String nome_morador, int status) {
        super();
        this.usuario = usuario;
        this.area = area;
        this.data = data;
        this.hora = hora;
        this.nome_morador = nome_morador;
        this.status = status;
        this.comentarios = new ArrayList<ComentarioReserva>();
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AreaComum getArea() {
        return area;
    }

    public void setArea(AreaComum area) {
        this.area = area;
    }

    public List<ComentarioReserva> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioReserva> comentarios) {
        this.comentarios = comentarios;
    }
    
    public void addComentario(ComentarioReserva comentario) {
        this.comentarios.add(comentario);
    }
    
    public void removeComentario(ComentarioReserva comentario) {
        this.comentarios.remove(comentario);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNome_morador() {
        return nome_morador;
    }

    public void setNome_morador(String nome_morador) {
        this.nome_morador = nome_morador;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId_reserva() {
        return id_reserva;
    }
    
    
    
    

}
