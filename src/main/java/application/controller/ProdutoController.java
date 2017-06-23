package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.repository.ProdutoRepository;
import application.resource.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @RequestMapping("/")
    public HttpEntity<List<Produto>> index() {
        final List<Produto> produtos = repository.findAll();
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

}
