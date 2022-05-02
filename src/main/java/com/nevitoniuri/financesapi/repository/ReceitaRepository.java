package com.nevitoniuri.financesapi.repository;

import com.nevitoniuri.financesapi.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Page<Receita> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    Optional<Receita> findByDescricaoIgnoreCase(String descricao);
}
