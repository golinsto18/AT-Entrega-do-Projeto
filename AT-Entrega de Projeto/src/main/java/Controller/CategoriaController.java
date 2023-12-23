package Controller;

import DTO.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.listarCategoriasComProdutos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obterCategoriaPorId(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.obterCategoriaPorIdComProdutos(id);
        return categoria != null
                ? new ResponseEntity<>(categoria, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/incluir")
    public ResponseEntity<CategoriaDTO> incluirCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO novaCategoria = categoriaService.incluirCategoria(categoriaDTO);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
