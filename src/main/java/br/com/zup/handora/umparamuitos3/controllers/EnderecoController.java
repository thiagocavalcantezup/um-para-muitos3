package br.com.zup.handora.umparamuitos3.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.handora.umparamuitos3.models.Endereco;
import br.com.zup.handora.umparamuitos3.models.EnderecoDTO;
import br.com.zup.handora.umparamuitos3.models.Zupper;
import br.com.zup.handora.umparamuitos3.repositories.EnderecoRepository;
import br.com.zup.handora.umparamuitos3.repositories.ZupperRepository;

@RestController
@RequestMapping(ZupperController.BASE_URI + "/{zupperId}" + EnderecoController.BASE_URI)
public class EnderecoController {

    public final static String BASE_URI = "/enderecos";

    private final ZupperRepository zupperRepository;
    private final EnderecoRepository enderecoRepository;

    public EnderecoController(ZupperRepository zupperRepository,
                              EnderecoRepository enderecoRepository) {
        this.zupperRepository = zupperRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(@PathVariable Long zupperId,
                                       @RequestBody @Valid EnderecoDTO enderecoDTO,
                                       UriComponentsBuilder ucb) {
        Zupper zupper = zupperRepository.findById(zupperId)
                                        .orElseThrow(
                                            () -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Não existe um zupper com o ID fornecido."
                                            )
                                        );
        Endereco endereco = enderecoRepository.save(enderecoDTO.toModel());

        zupper.adicionar(endereco);
        zupperRepository.save(zupper);

        URI location = ucb.path(ZupperController.BASE_URI + "/{zupperId}" + BASE_URI + "/{id}")
                          .buildAndExpand(zupperId, endereco.getId())
                          .toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long zupperId, @PathVariable Long id) {
        Zupper zupper = zupperRepository.findById(zupperId)
                                        .orElseThrow(
                                            () -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Não existe um zupper com o id informado."
                                            )
                                        );

        Endereco endereco = new Endereco(id);

        if (zupper.getEnderecos().contains(endereco)) {
            enderecoRepository.delete(endereco);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Não existe um endereço com o id informado associado ao zupper com o id informado."
            );
        }

        return ResponseEntity.noContent().build();
    }

}
