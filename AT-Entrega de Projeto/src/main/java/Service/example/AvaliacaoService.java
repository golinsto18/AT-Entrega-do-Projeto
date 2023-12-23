package Service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final ClienteService clienteService; // Injetar o serviço do cliente

    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, ClienteService clienteService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.clienteService = clienteService;
    }

    public List<AvaliacaoDTO> listarAvaliacoesComCliente() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public AvaliacaoDTO obterAvaliacaoPorIdComCliente(Long id) {
        Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);
        return avaliacaoOptional.map(this::converterParaDTO).orElse(null);
    }

    public AvaliacaoDTO incluirAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = converterDTOparaAvaliacao(avaliacaoDTO);
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        return converterParaDTO(novaAvaliacao);
    }

    public void excluirAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    private AvaliacaoDTO converterParaDTO(Avaliacao avaliacao) {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setNota(avaliacao.getNota());
        avaliacaoDTO.setComentario(avaliacao.getComentario());
        avaliacaoDTO.setDataAvaliacao(avaliacao.getDataAvaliacao());

        if (avaliacao.getCliente() != null) {
            avaliacaoDTO.setCliente(clienteService.converterParaDTO(avaliacao.getCliente()));
        }

        return avaliacaoDTO;
    }
}