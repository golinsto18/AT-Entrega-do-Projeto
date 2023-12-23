package Service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService; // Adicione o serviço de Categoria

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public List<ProdutoDTO> listarProdutosPorCategoria(Categoria categoria) {
        List<Produto> produtos = produtoRepository.findByCategoriaProduto(categoria);
        return produtos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO incluirProduto(ProdutoDTO produtoDTO) {
        // Mapeie os atributos do DTO para a entidade Produto
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setDetalhesTransporte(produtoDTO.getDetalhesTransporte());

        Categoria categoria = categoriaService.obterCategoriaPorNome(produtoDTO.getCategoria());
        produto.setCategoriaProduto(categoria);

        Produto novoProduto = produtoRepository.save(produto);
        return converterParaDTO(novoProduto);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO converterParaDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setCategoria(produto.getCategoriaProduto().getNomeCategoria());
        produtoDTO.setDetalhesTransporte(produto.getDetalhesTransporte());
        return produtoDTO;
    }
}
