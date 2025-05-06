package verde_sabido.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import verde_sabido.model.Alternativas;
import verde_sabido.model.Pergunta;
import verde_sabido.services.AlternativaService;

import java.util.List;

@RestController
@RequestMapping("/api/alternativa")
public class AlternativaController {

    private final AlternativaService alternativasService;

    public AlternativaController(AlternativaService alternativasService) {
        this.alternativasService = alternativasService;
    }

    // POST - CREATE
    @PostMapping
    public ResponseEntity<Alternativas> criar(@RequestBody Alternativas alternativa) {
        Alternativas novaAlternativas = alternativasService.criar(alternativa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAlternativas);
    }

    // GET - READ ALL
    @GetMapping
    public List<Alternativas> listarTodas() {
        return alternativasService.listarTodas();
    }

    // GET - READ BY ID
    @GetMapping("/{id}")
    public Alternativas buscarPorId(@PathVariable Long id) {
        return alternativasService.buscarPorId(id);
    }

    // GET - READ BY Pergunta
    @GetMapping("/pergunta/{id}")
    public List buscarPorPergunta(@PathVariable Long id) {
        return alternativasService.listarPorPergunta(id);
    }

    // PUT - UPDATE
    @PutMapping("/{id}")
    public Alternativas atualizar(@PathVariable Long id, @RequestBody Alternativas alternativa) {
        return alternativasService.atualizar(id, alternativa);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        alternativasService.deletar(id);
    }
}