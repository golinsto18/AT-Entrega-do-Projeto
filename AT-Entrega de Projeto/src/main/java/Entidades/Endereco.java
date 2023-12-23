package Entidades;
import javax.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
