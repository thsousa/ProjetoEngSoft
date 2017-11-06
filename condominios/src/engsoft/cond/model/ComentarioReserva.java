package engsoft.cond.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ComentarioReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comentario_Reserva;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Usuario usuario;

    @Basic
    private String datahora;

    @Basic
    private String texto;

    public ComentarioReserva() {

    }



    public ComentarioReserva(Usuario usuario, String datahora, String texto) {
        super();
        this.usuario = usuario;
        this.datahora = datahora;
        this.texto = texto;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId_comentario_Reserva() {
        return id_comentario_Reserva;
    }



}
