package com.Grande2.Gente2Controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Grande2.Gente2Model.Gente2Model;
import com.Grande2.Gente2Service.Gente2Service;

@RestController
@RequestMapping("/gente2")
public class Gente2Controller {

    @Autowired
    private Gente2Service gente2Service;

    // Endpoint para criar um novo Gente2
    @PostMapping
    public ResponseEntity<Gente2Model> criarGente2(@RequestBody Gente2Model gente2) {
        Gente2Model novoGente2 = gente2Service.salvarGente2(gente2);
        return ResponseEntity.ok(novoGente2);
    }

    // Endpoint para listar todos os Gente2
    @GetMapping
    public List<Gente2Model> listarTodos() {
        return gente2Service.listarTodos();
    }

    // Endpoint para buscar um Gente2 pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Gente2Model> buscarPorId(@PathVariable Long id) {
        Optional<Gente2Model> gente2 = gente2Service.buscarPorId(id);
        return gente2.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar o nome de um Gente2 pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Gente2Model> atualizarNome(@PathVariable Long id, @RequestBody String novoNome) {
        Gente2Model atualizado = gente2Service.atualizarNome(id, novoNome);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    // Endpoint para excluir um Gente2 pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Long id) {
        boolean excluido = gente2Service.excluirPorId(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Endpoint para fazer upload da imagem de perfil
    @PostMapping("/{id}/imagem")
    public ResponseEntity<Void> uploadImagem(@PathVariable Long id, @RequestParam("imagem") byte[] imagem) {
        boolean atualizado = gente2Service.atualizarImagem(id, imagem);
        return atualizado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Endpoint para recuperar a imagem de perfil
    @GetMapping("/{id}/imagem")
    public ResponseEntity<String> obterImagem(@PathVariable Long id) {
        Optional<Gente2Model> gente2 = gente2Service.buscarPorId(id);
        if (gente2.isPresent() && gente2.get().getImagem() != null) {
            String base64Image = Base64.getEncoder().encodeToString(gente2.get().getImagem());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(base64Image);
        }
        return ResponseEntity.notFound().build();
    }
}
