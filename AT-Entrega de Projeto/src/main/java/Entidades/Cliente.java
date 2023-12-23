package Entidades;

public class Cliente {
    @Entity
    public static class Cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;
        private String cpf;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "endereco_id")
        private Endereco endereco;

        private String telefone;
        private LocalDateTime dataRegistro;
        private String email;

        @OneToMany(mappedBy = "cliente")
        private List<Pedido.Pedido> pedidos = new ArrayList<>();

        private boolean ativo;
    }
}
