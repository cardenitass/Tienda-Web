package com.tienda.service;

import com.tienda.entity.Pais;
import com.tienda.repository.PaisRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> listCountry() {
        return (List<Pais>) paisRepository.findAll();
    }
}
