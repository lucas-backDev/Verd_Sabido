package verde_sabido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import verde_sabido.model.Alternativas;
import verde_sabido.model.Pergunta;

import java.util.List;

public interface AlternativaRepository extends JpaRepository<Alternativas, Long> {
    List<Alternativas> findByCodigoPergunta(Long codigoPergunta);


}
