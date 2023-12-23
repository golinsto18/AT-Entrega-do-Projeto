package Service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService; // Injetar o serviço do cliente

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
    }

    public List<PedidoDTO> listarPedidosComCliente() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO obterPedidoPorIdComCliente(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.map(this::converterParaDTO).orElse(null);
    }

    public PedidoDTO incluirPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = converterDTOparaPedido(pedidoDTO);
        Pedido novoPedido = pedidoRepository.save(pedido);
        return converterParaDTO(novoPedido);
    }

    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO converterParaDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setDataCompra(pedido.getDataCompra());
        pedidoDTO.setStatus(pedido.getStatus());
        pedidoDTO.setTotalPedido(pedido.getTotalPedido());

        if (pedido.getCliente() != null) {
            pedidoDTO.setCliente(clienteService.converterParaDTO(pedido.getCliente()));
        }

        return pedidoDTO;
    }
}

