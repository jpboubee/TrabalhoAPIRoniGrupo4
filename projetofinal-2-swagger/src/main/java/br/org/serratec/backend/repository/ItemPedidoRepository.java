package br.org.serratec.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Pedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	public List<ItemPedido> findAllByPedido(Pedido pedido);
}