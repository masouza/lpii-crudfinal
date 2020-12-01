package edu.usj.crudfinal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LocadorRepository extends CrudRepository<Locador, Long>{
    
    List<Locador> findAll();
    List<Locador> findByNome(String nome);
    List<Locador> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
    
}
