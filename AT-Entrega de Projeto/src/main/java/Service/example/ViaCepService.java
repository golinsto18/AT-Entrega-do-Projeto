package Service.example;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/";

    private final RestTemplate restTemplate;

    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Endereco consultarCep(String cep) {
        String url = VIACEP_URL + cep + "/json";
        return restTemplate.getForObject(url, Endereco.class);
    }
}
