package engsoft.cond.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author ogawa
 *
 */

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    

    @OneToMany(fetch = FetchType.EAGER)
    private List<Condominio> condominios;

    @Column(unique=true)
    private String email;
    
    @Basic 
    private String nome;

    @Column(unique=true)
    private String cpf;

    @Column(unique=true)
    private String cnpj;

    @Basic 
    private String tel1;

    @Basic 
    private String tel2;

    // 'm' morador, 's' sindico, 'f' funcionario, 'a' administradora, 'x' sysadm
    @Basic 
    private String nivel_acesso;    

    @Basic
    private String bloco;

    @Basic
    private String apartamento;
    
    public Usuario() {
        this.condominios = new ArrayList<Condominio>();       
    }
    
    public Usuario(String email, String nome, String cpf, String cnpj, String tel1,
            String tel2, String nivel_acesso) {
        super();
        this.condominios = new ArrayList<Condominio>();
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.nivel_acesso = nivel_acesso;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(String nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public int getId_usuario() {
        return id_usuario;
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
    
    @Override
    public String toString() {
        return id_usuario + " - " + email + " - " + nome + " - Nivel: " + nivel_acesso;
    }
}
