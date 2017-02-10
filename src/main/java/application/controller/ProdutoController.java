package application.controller;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.resource.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {


    @RequestMapping("/")
    public HttpEntity<Produto> index() {
        final Produto produto = new Produto();
        produto.setNome("Carro");
        produto.setPreco(350000);
        produto.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ProdutoController.class).index())
                .withSelfRel());
        return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }

}
