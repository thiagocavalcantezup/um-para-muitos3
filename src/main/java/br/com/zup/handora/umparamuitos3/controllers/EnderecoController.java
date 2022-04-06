package br.com.zup.handora.umparamuitos3.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.handora.umparamuitos3.repositories.ZupperRepository;

@RestController
@RequestMapping(ZupperController.BASE_URI + "/{zupperId}" + EnderecoController.BASE_URI)
public class EnderecoController {

    public final static String BASE_URI = "/enderecos";

    private final ZupperRepository zupperRepository;

    public EnderecoController(ZupperRepository zupperRepository) {
        this.zupperRepository = zupperRepository;
    }

}
