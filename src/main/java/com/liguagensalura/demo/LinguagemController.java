package com.liguagensalura.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.desktop.OpenFilesHandler;
import java.util.*;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*")
@RestController
public class LinguagemController {


    @Autowired
    private LinguagemRepository repository;

     @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens(){
        List<Linguagem> linguagemList = repository.findAll();
        return linguagemList;
    }
    @GetMapping("/linguagens/ranking")
    public List<Linguagem> obtetListaRankeada(){
        List<Linguagem> listRanked = repository.findAll(Sort.by(Sort.Direction.DESC,"rating"));
//        List<Linguagem> listRanked = listNotRanked.stream()
//                .sorted(Comparator.comparing(Linguagem::getRating))
//                .collect(Collectors.toList());
        return listRanked;
    }

    @GetMapping("/linguagens/votos")
    public List<Linguagem> obterListaVotos(){
         List<Linguagem> listVotes =  repository.findAll(Sort.by(Sort.Direction.DESC,"votess"));
        return listVotes;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastroLinguagem(@RequestBody Linguagem linguagem){
        Linguagem linguagemSalva = repository.save(linguagem);
        return ResponseEntity.ok(linguagemSalva);
    }

    @PostMapping("/linguagens/votar")
    public ResponseEntity<Linguagem> cadastrarVoto(@RequestBody Voto voto){
        Voto cadastarVoto = new Voto(voto.id(), voto.voto());

        Linguagem linguagemEscolhida = repository.findById(cadastarVoto.id()).get();

        double ratingAtual = linguagemEscolhida.getRating();
        double votacaoNova = (ratingAtual + cadastarVoto.voto())/2;
        linguagemEscolhida.setRating((float) (Math.round(votacaoNova*100)/100.0));
        linguagemEscolhida.setVotes((int)linguagemEscolhida.getVotes() + 1);

        return ResponseEntity.status(200).body(repository.save(linguagemEscolhida));
    }

    @PutMapping("/linguagens/{id}")
    public ResponseEntity<Linguagem> atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        Linguagem novaLinguagem = repository.findById(id).orElseThrow();
        novaLinguagem.setTitle(linguagem.getTitle());
        novaLinguagem.setImage(linguagem.getImage());
        novaLinguagem.setRating(linguagem.getRating());
        novaLinguagem.setVotes(linguagem.getVotes());
        repository.save(novaLinguagem);
        return ResponseEntity.ok(novaLinguagem);
    }
    @DeleteMapping("linguagens/{id}")
    public String deletarLinguagem(@PathVariable String id){
        try{
            repository.deleteById(id);
            return "Apagado com sucesso";
        }catch (Exception e){
            return "Id não encontrado";
        }

    }
}



