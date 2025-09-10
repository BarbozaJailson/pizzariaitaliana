package com.itb.mif3an.pizzariaitaliana.controller;

import com.itb.mif3an.pizzariaitaliana.exceptions.BadRequest;
import com.itb.mif3an.pizzariaitaliana.exceptions.NotFound;
import com.itb.mif3an.pizzariaitaliana.model.entity.Produto;
import com.itb.mif3an.pizzariaitaliana.model.repository.ProdutoRepository;
import com.itb.mif3an.pizzariaitaliana.model.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> produto(){
        List<Produto> produto = produtoService.findAll();
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/produto").toUriString());

        return ResponseEntity.created(uri).body(produtoService.save(produto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable (value = "id") String id){
        try{
            return ResponseEntity.ok(produtoService.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            throw new BadRequest("'" + id + "' não é um numero inteiro válido. Por favor, forneça um valor ineriro como 10.");
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@RequestBody Produto produto, @PathVariable(value = "id") String id){
        try{
            return ResponseEntity.ok().body(produtoService.update(produto, Long.parseLong(id)));
        } catch (NumberFormatException e) {
            throw new BadRequest("'" + id + "' não é um numero inteiro válido. Por favor, forneça um valor ineriro como 10.");
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> remover(@PathVariable(value = "id") String id){
        try{
            produtoService.delete(Long.parseLong(id));
            return ResponseEntity.ok().body("Produto com id " + id + " excluido com sucesso.");
        } catch (NumberFormatException e) {
            throw new BadRequest("'" + id + "' não é um numero inteiro válido. Por favor, forneça um valor ineriro como 10.");
        }
    }
}