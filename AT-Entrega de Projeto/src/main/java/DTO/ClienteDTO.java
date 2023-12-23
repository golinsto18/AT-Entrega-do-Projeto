package DTO;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDateTime dataRegistro;
    private String email;
    private EnderecoDTO endereco; // Representação do endereço
}
