package com.example.demo.controllers;

import com.example.demo.entities.Localizacao;
import com.example.demo.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public ResponseEntity<List<Localizacao>> getAllLocalizacoes() {
        List<Localizacao> localizacoes = localizacaoRepository.findAll();
        return new ResponseEntity<>(localizacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> getLocalizacaoById(@PathVariable Long id) {
        Optional<Localizacao> localizacaoOptional = localizacaoRepository.findById(id);
        return localizacaoOptional.map(localizacao -> new ResponseEntity<>(localizacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Localizacao> createLocalizacao(@RequestBody Localizacao localizacao) {
        Localizacao savedLocalizacao = localizacaoRepository.save(localizacao);
        return new ResponseEntity<>(savedLocalizacao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalizacao(@PathVariable Long id) {
        if (localizacaoRepository.existsById(id)) {
            localizacaoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}