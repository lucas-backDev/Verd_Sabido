package verde_sabido.services;
import verde_sabido.RegraNegocioException;
import verde_sabido.model.Pergunta;
import verde_sabido.repository.PerguntaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PerguntaService {
    private final PerguntaRepository repository;

    public PerguntaService(PerguntaRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Pergunta criar(Pergunta pergunta) {
        if (pergunta.getDescricao() == null || pergunta.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição da pergunta é obrigatória");
        }
        if (pergunta.getDescricao().length() > 500) {
            throw new IllegalArgumentException("Descrição não pode exceder 500 caracteres");
        }
        pergunta.setDataAtualizacao(LocalDateTime.now());
        pergunta.setEstadoAtual('A');
        return repository.save(pergunta);
    }

    // READ
    public List<Pergunta> listarTodas() {
        return repository.findAll();
    }

    public Pergunta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }

    // UPDATE
    public Pergunta atualizar(Long id, Pergunta perguntaAtualizada) {
        return repository.findById(id)
                .map(pergunta -> {
                    pergunta.setDescricao(perguntaAtualizada.getDescricao());
                    pergunta.setUnidade(perguntaAtualizada.getUnidade());
                    pergunta.setDataAtualizacao(LocalDateTime.now());
                    return repository.save(pergunta);
                })
                .orElseGet(() -> {
                    perguntaAtualizada.setId(id);
                    return repository.save(perguntaAtualizada);
                });
    }

    // DELETE
    public Pergunta deletar(Long id) {
        repository.findById(id)
                .map(pergunta -> {
                    pergunta.setEstadoAtual('I');
                    return repository.save(pergunta);
                });

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }
}