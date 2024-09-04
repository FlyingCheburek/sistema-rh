package com.apprh.app_rh.repositories;

import java.util.List;
import com.apprh.app_rh.models.Candidato;
import org.springframework.data.repository.CrudRepository;
import com.apprh.app_rh.models.Vaga;
import com.apprh.app_rh.models.Candidato;

public interface CandidatoRepository extends CrudRepository<Candidato, Long>  {
    Candidato findById(long id);
    Iterable<Candidato> findByVaga(Vaga vaga);    
    Candidato findByRg(String rg);
    List<Candidato> findByNomeCandidato(String nome);
}
