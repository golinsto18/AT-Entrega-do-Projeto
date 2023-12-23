package loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class EnderecoLoader implements CommandLineRunner {

    private final ClienteService clienteService;
    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoLoader(ClienteService clienteService, EnderecoService enderecoService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
    }

    @Override
    public void run(String... args) {

        String cep = "CEP_DE_EXEMPLO";

        // Consulta a API de CEP
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ViaCepResponse viaCepResponse = restTemplate.getForObject(url, ViaCepResponse.class);

        if (Objects.nonNull(viaCepResponse)) {
            // Cria um novo endereço com base nos dados da API
            Endereco endereco = new Endereco();
            endereco.setCep(viaCepResponse.getCep());
            endereco.setLogradouro(viaCepResponse.getLogradouro());
            endereco.setBairro(viaCepResponse.getBairro());
            endereco.setCidade(viaCepResponse.getLocalidade());
            endereco.setUf(viaCepResponse.getUf());

            // Associa o endereço a um cliente existente (substitua o ID pelo cliente desejado)
            Cliente cliente = clienteService.obterClientePorId(1L);
            endereco.setCliente(cliente);

            // Adiciona o endereço usando o serviço
            enderecoService.criarEndereco(endereco);
        }
    }
}
