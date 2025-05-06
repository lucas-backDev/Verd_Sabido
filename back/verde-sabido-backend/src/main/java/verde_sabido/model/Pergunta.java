package verde_sabido.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bil_pergunta")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_pergunta")
    private Long id;

    @Column(name = "ds_pergunta", nullable = false, length = 500)
    private String descricao;

    @Column(name = "ds_unidade", length = 50)
    private String unidade;

    @Column(name = "cd_estado_atual")
    private char estadoAtual = 'A';

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "codigoPergunta", cascade = CascadeType.ALL)
    private Set<Alternativas> alternativas = new HashSet<Alternativas>();

    // Construtores
    public Pergunta() {
    }

    public Pergunta(String descricao, String unidade) {
        this.descricao = descricao;
        this.unidade = unidade;
        this.dataAtualizacao = LocalDateTime.now();
        this.estadoAtual = 'A';
        this.dataCriacao = LocalDateTime.now();
    }

//    public void addAlternativa(Alternativas alternativa) {
//        this.alternativas.add(alternativa);
//        alternativa.setCodigoPergunta(this);
//    }
//
//    public void removeAlternativa(Alternativas alternativa) {
//        this.alternativas.remove(alternativa);
//        alternativa.setCodigoPergunta(null);
//    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public char getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Character estadoAtual) {
        this.estadoAtual = (estadoAtual != null) ? estadoAtual : 'A';
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}