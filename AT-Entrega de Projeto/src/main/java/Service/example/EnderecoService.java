package Service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    @Transactional
    public void incluirEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }
    @Transactional(readOnly = true)
    public Optional<Endereco> buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Endereco> buscarEnderecosPorCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }
}

