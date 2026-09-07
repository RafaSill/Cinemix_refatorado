package Persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class AlugarFilmes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Users users;
    
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filmes filmes;
    
    @ManyToOne
    @JoinColumn(name = "tipoPagamento_id")
    private TipoPagamento tipoPagamento;
    
    private LocalDate dataInicio, dataDevolucao;
    private double valor;
    private String status;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Filmes getFilmes() {
        return filmes;
    }

    public void setFilmes(Filmes filmes) {
        this.filmes = filmes;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    
    
}
