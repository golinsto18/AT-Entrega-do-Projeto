package loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PedidoLoader implements CommandLineRunner {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;

    @Autowired
    public PedidoLoader(PedidoService pedidoService, ClienteService clienteService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }

    @Override
    public void run(String... args) {
        // Criar um pedido
        Pedido pedido = new Pedido();
        pedido.setDataCompra(LocalDateTime.now());
        pedido.setStatus("Em andamento");
        pedido.setTotalPedido(100.0);

        // Associar cliente ao pedido
        Cliente cliente = clienteService.obterClientePorId(1L);
        pedido.setCliente(cliente);

        // Adicionar pedido usando o serviço
        pedidoService.criarPedido(pedido);
    }
}


