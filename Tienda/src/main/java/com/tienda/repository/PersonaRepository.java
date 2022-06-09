package com.tienda.repository;


import com.tienda.entity.Persona;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author davidcardenas
 */

@Repository
public interface PersonaRepository extends CrudRepository<Persona,Long> {
    
}
