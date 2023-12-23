package loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClienteLoader implements CommandLineRunner {
    private final ClienteService clienteService;

    @Autowired
    public ClienteLoader(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public void run(String... args) {
        // Criar um cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Nome do Cliente");
        cliente.setCpf("123.456.789-00");
        cliente.setEndereco("Endereço do Cliente");
        cliente.setTelefone("123456789");
        cliente.setDataRegistro(LocalDateTime.now());
        cliente.setEmail("cliente@example.com");

        // Adicionar cliente usando o serviço
        clienteService.criarCliente(cliente);
    }
}


