package Entidades;

public class Produto {
    @Entity
    public static class Produto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;
        private String descricao;
        private double preco;
        private String categoria;
        private String detalhesTransporte;

        @ManyToMany(mappedBy = "produtos")
        private List<Pedido> pedidos = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "categoria_id")
        private Categoria.Categoria categoriaProduto;

        private boolean ativo;
    }
}
