package loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AvaliacaoLoader implements CommandLineRunner {
    private final AvaliacaoService avaliacaoService;
    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    @Autowired
    public AvaliacaoLoader(AvaliacaoService avaliacaoService, ClienteService clienteService, PedidoService pedidoService) {
        this.avaliacaoService = avaliacaoService;
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
    }

    @Override
    public void run(String... args) {
        // Criar uma avaliação
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5);
        avaliacao.setComentario("Ótimo serviço!");
        avaliacao.setDataAvaliacao(LocalDateTime.now());

        // Associar cliente à avaliação
        Cliente cliente = clienteService.obterClientePorId(1L);
        avaliacao.setCliente(cliente);

        // Associar pedido à avaliação
        Pedido pedido = pedidoService.obterPedidoPorId(1L);
        avaliacao.setPedido(pedido);

        // Adicionar avaliação usando o serviço
        avaliacaoService.criarAvaliacao(avaliacao);
    }
}
