package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.resource.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
