package com.itb.mif3an.pizzariaitaliana.model.service;

import com.itb.mif3an.pizzariaitaliana.exceptions.NotFound;
import com.itb.mif3an.pizzariaitaliana.model.entity.Categoria;
import com.itb.mif3an.pizzariaitaliana.model.entity.Produto;
import com.itb.mif3an.pizzariaitaliana.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{

    private ProdutoRepository produtoRepository;

    @Override
    public Produto findById(Long id){
        try{
            return produtoRepository.findById(id).get();
        }catch (Exception e) {
            throw new NotFound("Produto não encontrado com id: " + id);
        }
    }
    @Override
    public List<Produto> findAll(){
        return this.produtoRepository.findAll();
    }
    @Override
    public Produto save(Produto produto){
        produto.setCodStatus(true);
        // é possivel salvar o produto sem categoria, pois a chave estrangeira é NULL
        if(produto.getCategoria() != null){
            //Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
        }
    return produtoRepository.save(produto);
    }
    @Override
    public void delete(Long id){
        if (!produtoRepository.existsById(id)){
           throw new NotFound("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }
    @Override
    public Produto update(Produto produto, Long id){
        if (!produtoRepository.existsById(id)){
            throw new NotFound("Produto com id: " + id);
        }
        Produto newProduto = produtoRepository.findById(id).get();
        newProduto.setNome(produto.getNome());
        newProduto.setDescricao(produto.getDescricao());
        newProduto.setTipo(produto.getTipo());
        newProduto.setValorVenda(produto.getValorVenda());
        newProduto.setValorCompra(produto.getValorCompra());
        newProduto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        newProduto.setCodStatus(produto.isCodStatus());
        return produtoRepository.save(newProduto);
    }
}
