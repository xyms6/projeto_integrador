package com.Grande2.GenteRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grande2.Gente2Model.Gente2Model;

@Repository
public interface Gente2Repository extends JpaRepository<Gente2Model, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
}
