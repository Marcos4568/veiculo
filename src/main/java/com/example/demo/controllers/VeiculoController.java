package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entities.Veiculo;
import com.example.demo.repositories.VeiculoRepository;
@RestController
@RequestMapping("/veiculos")

public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public ResponseEntity<List<Veiculo>> getAllVeiculos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        return veiculoOptional.map(veiculo -> new ResponseEntity<>(veiculo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoRepository.save(veiculo);
        return new ResponseEntity<>(savedVeiculo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }   
}
