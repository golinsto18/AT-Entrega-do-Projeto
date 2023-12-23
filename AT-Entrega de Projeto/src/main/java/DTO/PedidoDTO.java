package DTO;

public class PedidoDTO {
    private Long id;
    private LocalDateTime dataCompra;
    private String status;
    private double totalPedido;
    private ClienteDTO cliente; // Representação do cliente
}
