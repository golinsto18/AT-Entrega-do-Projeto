package Entidades;

public class Pedido {
    @Entity
    public static class Pedido {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime dataCompra;
        private String status;
        private double totalPedido;

        @ManyToOne
        @JoinColumn(name = "cliente_id")
        private Cliente.Cliente cliente;

        @ManyToMany
        @JoinTable(
                name = "pedido_produto",
                joinColumns = @JoinColumn(name = "pedido_id"),
                inverseJoinColumns = @JoinColumn(name = "produto_id")
        )
        private List<Produto> produtos = new ArrayList<>();

        private boolean ativo;
    }
}
