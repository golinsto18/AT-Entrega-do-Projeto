package Entidades;

public class Categoria {
    @Entity
    public static class Categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nomeCategoria;
        private String descricao;

        @OneToMany(mappedBy = "categoriaProduto")
        private List<Produto.Produto> produtos = new ArrayList<>();

        private boolean ativo;
    }
}
