package br.com.zup.handora.umparamuitos3.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.handora.umparamuitos3.repositories.ZupperRepository;

@RestController
@RequestMapping(ZupperController.BASE_URI)
public class ZupperController {

    public final static String BASE_URI = "/zuppers";

    private final ZupperRepository zupperRepository;

    public ZupperController(ZupperRepository zupperRepository) {
        this.zupperRepository = zupperRepository;
    }

}
