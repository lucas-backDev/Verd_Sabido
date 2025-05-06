package verde_sabido.controller;

import verde_sabido.services.PerguntaService;
import verde_sabido.model.Pergunta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pergunta")
public class PerguntaController {

    private final PerguntaService perguntaService;

    public PerguntaController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    // POST - CREATE
    @PostMapping
    public ResponseEntity<Pergunta> criar(@RequestBody Pergunta pergunta) {
        Pergunta novaPergunta = perguntaService.criar(pergunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPergunta);
    }

    // GET - READ ALL
    @GetMapping
    public List<Pergunta> listarTodas() {
        return perguntaService.listarTodas();
    }

    // GET - READ BY ID
    @GetMapping("/{id}")
    public Pergunta buscarPorId(@PathVariable Long id) {
        return perguntaService.buscarPorId(id);
    }

    // PUT - UPDATE
    @PutMapping("/{id}")
    public Pergunta atualizar(@PathVariable Long id, @RequestBody Pergunta pergunta) {
        return perguntaService.atualizar(id, pergunta);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        perguntaService.deletar(id);
    }
}