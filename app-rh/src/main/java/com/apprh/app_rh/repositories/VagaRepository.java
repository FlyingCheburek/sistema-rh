package com.apprh.app_rh.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import com.apprh.app_rh.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, Long> {
    Vaga findByCodigo(long Codigo);
    List<Vaga> findByNome(String nome);
}
