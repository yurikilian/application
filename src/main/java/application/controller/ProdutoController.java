package application.controller;

import java.util.List;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import application.repository.ProdutoRepository;
import application.resource.Produto;
import io.swagger.annotations.Api;

@Api(value = "Api de produtos da aplicação", tags = {
        "Api de produtos da aplicação" })
@Controller
@RequestMapping("/produto")
@ExposesResourceFor(Produto.class)
public class ProdutoController {

    private final ProdutoRepository repository;

    private final EntityLinks entityLinks;

    public ProdutoController(ProdutoRepository repository,
            EntityLinks entityLinks) {
        this.repository = repository;
        this.entityLinks = entityLinks;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Produto>> create(@RequestBody Produto produto) {
        Produto novoProduto = repository.save(produto);
        Resource<Produto> resource = new Resource<Produto>(novoProduto);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resource<Produto>> read(@PathVariable Long id) {
        Produto produto = this.repository.findOne(id);
        Resource<Produto> resource = new Resource<>(produto);
        resource.add(this.entityLinks.linkToSingleResource(Produto.class, id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resources<Produto>> read() {
        List<Produto> produtos = this.repository.findAll();
        Resources<Produto> resources = new Resources<>(produtos);
        resources.add(this.entityLinks.linkToCollectionResource(Produto.class));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
