package verde_sabido.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bil_alternativa")
public class Alternativas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_alternativa")
    private Long id;

    @Column(name = "ds_alternativa")
    private String descricao;

    @Column(name = "cd_estado_atual")
    private char estadoAtual = 'A';

    @Column(name = "ic_correta")
    private boolean estaCorreta;

    @UpdateTimestamp
    @Column(name = "dt_atualizacao")
    private LocalDateTime dataAtualizacao;

    @CreationTimestamp
    @Column(name = "dt_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @NotNull
    @Column(name = "cd_pergunta")
    private Long codigoPergunta;

    public Alternativas(String descricao, boolean estaCorreta, Long codigoPergunta) {
        this.descricao = descricao;
        this.estaCorreta = estaCorreta;
        this.codigoPergunta = codigoPergunta;
    }

    public Alternativas() {
    }

    public boolean getEstaCorreta() {
        return estaCorreta;
    }

    public void setEstaCorreta(boolean estaCorreta) {
        this.estaCorreta = estaCorreta;
    }

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

    public char getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(char estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getCodigoPergunta() {
        return codigoPergunta;
    }

    public void setCodigoPergunta(Long codigoPergunta) {
        this.codigoPergunta = codigoPergunta;
    }
}

