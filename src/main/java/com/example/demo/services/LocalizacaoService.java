package com.example.demo.services;

import com.example.demo.entities.Localizacao;
import com.example.demo.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public List<Localizacao> getAllLocalizacoes() {
        return localizacaoRepository.findAll();
    }

    public Optional<Localizacao> getLocalizacaoById(Long id) {
        return localizacaoRepository.findById(id);
    }

    public Localizacao createLocalizacao(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    public void deleteLocalizacao(Long id) {
        localizacaoRepository.deleteById(id);
    }
}