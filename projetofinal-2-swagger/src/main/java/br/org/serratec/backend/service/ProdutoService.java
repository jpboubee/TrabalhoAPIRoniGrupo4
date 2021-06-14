package br.org.serratec.backend.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoService fotoService;
	
	public ProdutoDTO buscarPorNome(String nome) {
		Optional<Produto> produto = produtoRepository.findByNome(nome);
		if(produto.isPresent()) {
			return new ProdutoDTO(produto.get());
		}else {
			return null;
		}
	}
	
	public ProdutoDTO adicionarFotoUrl(Produto produto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}/foto")
				.buildAndExpand(produto.getId()).toUri();

		System.out.println("URI" + uri);
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setUrl(uri.toString());
		return produtoDTO;
	}
	
	public List<ProdutoDTO> listar(){
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		List<Produto> produtos = produtoRepository.findAll();
		for (Produto produto : produtos) {
			ProdutoDTO produtoDTO = new ProdutoDTO(produto);
			produtosDTO.add(produtoDTO);
		}
		return produtosDTO;
	}
	
	public ProdutoDTO buscarPorId(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			return adicionarFotoUrl(produto.get());
		
		}else {
			return null;
		}
	}
	
	public Produto buscarProdutoPorId(Long id) {
		Optional<Produto> opProduto = produtoRepository.findById(id);
		if(opProduto.isPresent()) {
			return opProduto.get();
		} else {
			return null;
		}
	}
	
	public ProdutoDTO atualizar(Long id, Produto produto) {
		if(!produtoRepository.existsById(id)) {
			return null;
		}else {
			produto.setId(id);
			produto = produtoRepository.save(produto);
			return adicionarFotoUrl(produto);
		}
	}
	
	public Boolean deletar(Long id) {
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return true;
		}else
			return false;
	}
	
	public ProdutoDTO inserir(MultipartFile file, Produto produto) throws IOException {
		fotoService.inserir(produtoRepository.save(produto), file);
		return adicionarFotoUrl(produto);
	}
	
}