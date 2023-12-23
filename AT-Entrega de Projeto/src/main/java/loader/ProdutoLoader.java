package loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProdutoLoader implements CommandLineRunner {
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ProdutoLoader(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) {
        // Criar um produto
        Produto produto = new Produto();
        produto.setNome("Nome do Produto");
        produto.setDescricao("Descrição do Produto");
        produto.setPreco(50.0);
        produto.setCategoria("Categoria do Produto");
        produto.setDetalhesTransporte("Detalhes do Transporte");

        // Associar categoria ao produto
        Categoria categoria = categoriaService.obterCategoriaPorId(1L); // Substituir com o ID correto
        produto.setCategoriaProduto(categoria);

        // Adicionar produto usando o serviço
        produtoService.criarProduto(produto);
    }
}

