package com.nevitoniuri.financesapi.repository;

import com.nevitoniuri.financesapi.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByDescricaoContainingIgnoreCase(String descricao);
}
