package Entidades;

public class AvaliacaoFeedback {
    @Entity
    public static class AvaliacaoFeedback {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int nota;
        private String comentario;
        private LocalDateTime dataAvaliacao;

        @ManyToOne
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "pedido_id")
        private Pedido pedido;

        private boolean ativo;
    }
}