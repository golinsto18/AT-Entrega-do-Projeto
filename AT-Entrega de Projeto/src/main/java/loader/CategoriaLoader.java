package loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoriaLoader implements CommandLineRunner {
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaLoader(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) {
        // Criar uma categoria
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("Categoria A");
        categoria.setDescricao("Descrição da Categoria A");

        // Adicionar categoria usando o serviço
        categoriaService.criarCategoria(categoria);
    }
}
