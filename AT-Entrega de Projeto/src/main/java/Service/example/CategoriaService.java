package Service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ProdutoService produtoService; // Injetar o serviço do produto

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoService produtoService) {
        this.categoriaRepository = categoriaRepository;
        this.produtoService = produtoService;
    }

    public List<CategoriaDTO> listarCategoriasComProdutos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO obterCategoriaPorIdComProdutos(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.map(this::converterParaDTO).orElse(null);
    }

    public CategoriaDTO incluirCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = converterDTOparaCategoria(categoriaDTO);
        Categoria novaCategoria = categoriaRepository.save(categoria);
        return converterParaDTO(novaCategoria);
    }

    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO converterParaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
        categoriaDTO.setDescricao(categoria.getDescricao());

        if (categoria.getProdutos() != null) {
            categoriaDTO.setProdutos(produtoService.listarProdutosPorCategoria(categoria));
        }

        return categoriaDTO;
    }
}
