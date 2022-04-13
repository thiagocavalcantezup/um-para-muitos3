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

import br.com.zup.handora.umparamuitos3.models.Zupper;
import br.com.zup.handora.umparamuitos3.models.ZupperDTO;
import br.com.zup.handora.umparamuitos3.repositories.ZupperRepository;

@RestController
@RequestMapping(ZupperController.BASE_URI)
public class ZupperController {

    public final static String BASE_URI = "/zuppers";

    private final ZupperRepository zupperRepository;

    public ZupperController(ZupperRepository zupperRepository) {
        this.zupperRepository = zupperRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ZupperDTO zupperDTO,
                                       UriComponentsBuilder ucb) {
        Zupper zupper = zupperRepository.save(zupperDTO.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(zupper.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Zupper zupper = zupperRepository.findById(id)
                                        .orElseThrow(
                                            () -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Não existe um zupper com o id informado."
                                            )
                                        );

        zupperRepository.delete(zupper);

        return ResponseEntity.noContent().build();
    }

}
