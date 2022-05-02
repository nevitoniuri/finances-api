package com.nevitoniuri.financesapi.repository;

import com.nevitoniuri.financesapi.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Page<Despesa> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    Optional<Despesa> findByDescricaoIgnoreCase(String descricao);

    boolean existsByDescricao(String descricao);
}
