package com.nevitoniuri.financesapi.repository;

import com.nevitoniuri.financesapi.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Page<Despesa> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    boolean existsByDescricao(String descricao);
}
