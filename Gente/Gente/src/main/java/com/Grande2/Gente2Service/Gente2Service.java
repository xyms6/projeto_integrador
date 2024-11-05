package com.Grande2.Gente2Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grande2.Gente2Model.Gente2Model;
import com.Grande2.GenteRepository.Gente2Repository;

@Service
public class Gente2Service {

    @Autowired
    private Gente2Repository gente2Repository;

    public Gente2Model salvarGente2(Gente2Model gente2) {
        return gente2Repository.save(gente2);
    }

    public List<Gente2Model> listarTodos() {
        return gente2Repository.findAll();
    }

    public Optional<Gente2Model> buscarPorId(Long id) {
        return gente2Repository.findById(id);
    }

    public Gente2Model atualizarNome(Long id, String novoNome) {
        Optional<Gente2Model> optionalGente2 = buscarPorId(id);
        if (optionalGente2.isPresent()) {
            Gente2Model gente2 = optionalGente2.get();
            gente2.setNome(novoNome);
            return gente2Repository.save(gente2);
        }
        return null;
    }

    public boolean excluirPorId(Long id) {
        if (gente2Repository.existsById(id)) {
            gente2Repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean atualizarImagem(Long id, byte[] imagem) {
        Optional<Gente2Model> optionalGente2 = buscarPorId(id);
        if (optionalGente2.isPresent()) {
            Gente2Model gente2 = optionalGente2.get();
            gente2.setImagem(imagem);
            gente2Repository.save(gente2);
            return true;
        }
        return false;
    }
}
