package io.openliberty.guides.services;

import io.openliberty.guides.models.Oportunidad;
import io.openliberty.guides.repositories.OportunidadRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class OportunidadService {

    @Inject
    OportunidadRepository oportunidadRepository;

    // CREATE
    public void saveOportunidad(Oportunidad oportunidad) {
        oportunidadRepository.save(oportunidad);
    }

    // READ ALL
    public List<Oportunidad> getOportunidades() {
        return oportunidadRepository.findAll();
    }

    // READ BY CODIGO
    public Oportunidad getOportunidadByCodigo(String codigo) {
        return oportunidadRepository.findByCodigo(codigo);
    }

    // UPDATE
    public boolean updateOportunidad(String codigo, Oportunidad updatedOportunidad) {
        return oportunidadRepository.update(codigo, updatedOportunidad);
    }

    // DELETE
    public boolean deleteOportunidadByCodigo(String codigo) {
        return oportunidadRepository.deleteByCodigo(codigo);
    }
}
