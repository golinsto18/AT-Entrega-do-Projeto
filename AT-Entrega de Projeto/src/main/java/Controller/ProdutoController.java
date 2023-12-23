package Controller;

import DTO.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService; // Adicione o serviço de Categoria
    @Autowired
    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/por-categoria/{categoriaId}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPorCategoria(@PathVariable Long categoriaId) {
        Categoria categoria = categoriaService.obterCategoriaPorId(categoriaId);

        if (categoria != null) {
            List<ProdutoDTO> produtos = produtoService.listarProdutosPorCategoria(categoria);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/incluir")
    public ResponseEntity<ProdutoDTO> incluirProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO novoProduto = produtoService.incluirProduto(produtoDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
