package com.apprh.app_rh.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.apprh.app_rh.models.Candidato;
import com.apprh.app_rh.models.Vaga;
import com.apprh.app_rh.repositories.CandidatoRepository;
import com.apprh.app_rh.repositories.VagaRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class VagaController {
    @Autowired
    private VagaRepository vr;

    @Autowired
    private CandidatoRepository cr;

    @RequestMapping(value = "/cadastrarVaga", method=RequestMethod.GET)
    public String form() {
        return "vaga/cadastrarVaga";
    }
    
    @RequestMapping(value="/cadastarVaga", method=RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }
        vr.save(vaga);
        return "redirect:/cadastrarVaga";
    }
}
