package Service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public List<ClienteDTO> listarClientesComEndereco() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO obterClientePorIdComEndereco(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(this::converterParaDTO).orElse(null);
    }

    public ClienteDTO incluirCliente(ClienteDTO clienteDTO) {
        Cliente cliente = converterDTOparaCliente(clienteDTO);
        Cliente novoCliente = clienteRepository.save(cliente);
        return converterParaDTO(novoCliente);
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO converterParaDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setDataRegistro(cliente.getDataRegistro());
        clienteDTO.setEmail(cliente.getEmail());

        if (cliente.getEndereco() != null) {
            clienteDTO.setEndereco(converterEnderecoParaDTO(cliente.getEndereco()));
        }

       return clienteDTO;
    }

    private EnderecoDTO converterEnderecoParaDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());

        return enderecoDTO;
    }
}