package Controller;

import DTO.AvaliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoFeedbackController {
    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoFeedbackController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesComCliente() {
        List<AvaliacaoDTO> avaliacoes = avaliacaoService.listarAvaliacoesComCliente();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> obterAvaliacaoPorIdComCliente(@PathVariable Long id) {
        AvaliacaoDTO avaliacao = avaliacaoService.obterAvaliacaoPorIdComCliente(id);
        return avaliacao != null
                ? new ResponseEntity<>(avaliacao, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/incluir")
    public ResponseEntity<AvaliacaoDTO> incluirAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO novaAvaliacao = avaliacaoService.incluirAvaliacao(avaliacaoDTO);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAvaliacao(@PathVariable Long id) {
        avaliacaoService.excluirAvaliacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}