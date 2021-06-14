package br.org.serratec.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Cliente findByEmail (String email);
	public Cliente findByCpf(String cpf);
	public Cliente findByNomeUsuario(String nomeUsuario);
	public Page<ClienteDTO> findByNomeCompletoContainingIgnoreCase(String paramNome, Pageable pageable);
}