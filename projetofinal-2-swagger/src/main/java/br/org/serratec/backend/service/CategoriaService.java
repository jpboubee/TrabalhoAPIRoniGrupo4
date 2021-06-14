package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> listar() {
		List<CategoriaDTO> categoriasDTO = new ArrayList<CategoriaDTO>();
		List<Categoria> categorias = categoriaRepository.findAll();
		for (Categoria cat : categorias) {
			CategoriaDTO dto = new CategoriaDTO(cat); 
			categoriasDTO.add(dto);
		}
		return categoriasDTO;
	}

	public CategoriaDTO buscar(String nome) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(nome);
		if (categoria.isPresent()) {
			return new CategoriaDTO(categoria.get());
		}
		return null;
	}
	
	public CategoriaDTO inserir (Categoria categoria) {
		categoria = categoriaRepository.save(categoria);
		return new CategoriaDTO(categoria);
	}

	public CategoriaDTO atualizar(Long id, Categoria categoria) {
		if (categoriaRepository.existsById(id)) {
			categoria.setId(id);
			categoria = categoriaRepository.save(categoria);
			return new CategoriaDTO(categoria);
		}
		return null;
	}

	public Boolean deletar(Long id) {
		if (!categoriaRepository.existsById(id)) {
			return false;
		}
		categoriaRepository.deleteById(id);
		return true;
	}
}