package verde_sabido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import verde_sabido.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

}
