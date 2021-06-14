package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.enums.StatusPedido;
import br.org.serratec.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	public Pedido findByStatus(StatusPedido status);
}