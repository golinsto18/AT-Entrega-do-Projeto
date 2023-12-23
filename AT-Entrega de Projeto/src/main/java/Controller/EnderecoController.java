package Controller;

import DTO.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criarEndereco(@RequestBody Endereco endereco) {
        enderecoService.criarEndereco(endereco);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoService.buscarEnderecoPorId(id);
        return endereco.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/buscarPorCep/{cep}")
    public ResponseEntity<List<Endereco>> buscarEnderecosPorCep(@PathVariable String cep) {
        List<Endereco> enderecos = enderecoService.buscarEnderecosPorCep(cep);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
}
