package br.com.danielnunes.exercicio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.danielnunes.exercicio.entities.Contribuicao;
import br.com.danielnunes.exercicio.repositories.ContribuicaoRepository;

@RestController
@RequestMapping("/")
public class ContribuicaoController {
	
	@Autowired
    private ContribuicaoRepository contribuicaoRepository;
	
	@PostMapping("/novacontribuicao")
	public ResponseEntity<String> criarContribuicao(
            @RequestParam("name") String name,
            @RequestParam("descricao") String descricao,
            @RequestParam("imagem") MultipartFile imagem) {
        try {
            Contribuicao contribuicao = new Contribuicao();
            contribuicao.setName(name);
            contribuicao.setDescricao(descricao);
            contribuicao.setImagem(imagem.getBytes());
            contribuicaoRepository.save(contribuicao);
            return new ResponseEntity<>("Contribuição salva com sucesso!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Falha ao processar a imagem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/imagem/{id}")
	public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
	    Contribuicao contribuicao = contribuicaoRepository.findById(id).orElseThrow();
	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG)
	            .body(contribuicao.getImagem());
	}
	
	
}
