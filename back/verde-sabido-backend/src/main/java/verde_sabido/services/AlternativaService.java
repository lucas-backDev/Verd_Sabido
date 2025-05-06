package verde_sabido.services;

import org.springframework.stereotype.Service;
import verde_sabido.model.Alternativas;
import verde_sabido.model.Pergunta;
import verde_sabido.repository.AlternativaRepository;
import verde_sabido.repository.PerguntaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlternativaService {
    private final AlternativaRepository alternativaRepository;
    private final PerguntaRepository perguntaRepository;

    public AlternativaService(AlternativaRepository alternativaRepository,
                              PerguntaRepository perguntaRepository) {
        this.alternativaRepository = alternativaRepository;
        this.perguntaRepository = perguntaRepository;
    }

    // CREATE
    public Alternativas criar(Alternativas alternativa) {
        // Validações básicas
        if (alternativa.getDescricao() == null || alternativa.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição da alternativa é obrigatória");
        }

        String descricaoLimpa = alternativa.getDescricao().trim();
        if (descricaoLimpa.length() > 500) {
            throw new IllegalArgumentException("Descrição não pode exceder 500 caracteres");
        }

        if (alternativa.getCodigoPergunta() == null) {
            throw new IllegalArgumentException("Pergunta associada é obrigatória");
        }


        Long perguntaId = alternativa.getCodigoPergunta();

        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pergunta não encontrada com ID: " + perguntaId));

        Alternativas novaAlternativa = new Alternativas();
        novaAlternativa.setDescricao(descricaoLimpa);
        novaAlternativa.setEstaCorreta(alternativa.getEstaCorreta());
        novaAlternativa.setCodigoPergunta(perguntaId);
        novaAlternativa.setEstadoAtual('A');

        return alternativaRepository.save(novaAlternativa);
    }

    // READ
    public List<Alternativas> listarTodas() {
        return alternativaRepository.findAll();
    }

    public Alternativas buscarPorId(Long id) {
        return alternativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));
    }

    public List<Alternativas> listarPorPergunta(Long perguntaId) {
        if (!perguntaRepository.existsById(perguntaId)) {
            throw new IllegalArgumentException("Pergunta com ID " + perguntaId + " não encontrada.");
        }

        List<Alternativas> alternativas = alternativaRepository.findByCodigoPergunta(perguntaId);

        return alternativas;
    }


    // UPDATE
    public Alternativas atualizar(Long id, Alternativas alternativaAtualizada) {
        return alternativaRepository.findById(id)
                .map(alternativa -> {
                    // Atualiza apenas os campos permitidos
                    if (alternativaAtualizada.getDescricao() != null) {
                        alternativa.setDescricao(alternativaAtualizada.getDescricao());
                    }

                    Long perguntaId = alternativa.getCodigoPergunta();

                    Pergunta pergunta = perguntaRepository.findById(perguntaId)
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Pergunta não encontrada com ID: " + perguntaId));


                    alternativa.setEstaCorreta(alternativa.getEstaCorreta());
                    alternativa.setCodigoPergunta(perguntaId);
                    alternativa.setDataAtualizacao(LocalDateTime.now());
                    return alternativaRepository.save(alternativa);
                })
                .orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));
    }

    // DELETE (soft delete)
    public void deletar(Long id) {
        Alternativas alternativa = alternativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));

        alternativa.setEstadoAtual('I');
        alternativa.setDataAtualizacao(LocalDateTime.now());
        alternativaRepository.save(alternativa);
    }
}