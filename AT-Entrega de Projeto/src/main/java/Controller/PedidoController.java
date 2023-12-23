package Controller;

import DTO.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> pedidos = pedidoService.listarPedidosComCliente();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obterPedidoPorId(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.obterPedidoPorIdComCliente(id);
        return pedido != null
                ? new ResponseEntity<>(pedido, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/incluir")
    public ResponseEntity<PedidoDTO> incluirPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO novoPedido = pedidoService.incluirPedido(pedidoDTO);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

