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
    private VagaRepository vagaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @RequestMapping(value = "/cadastrarVaga", method=RequestMethod.GET)
    public String form() {
        return "vaga/formVaga";
    }
    
    @RequestMapping(value="/cadastarVaga", method=RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }
        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    @RequestMapping(value="/vagas")
    public ModelAndView listaVagas() {
        ModelAndView modelAndView = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        modelAndView.addObject("vagas", vagas);

        return modelAndView;
    }

    @RequestMapping(value="/{codigo}", method = RequestMethod.GET) 
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
        ModelAndView modelAndView = new ModelAndView("vaga/detalhesVaga");
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        modelAndView.addObject("vaga", vaga);
        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);
        modelAndView.addObject("candidatos", candidatos);
        return modelAndView;
    }

    public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/{codigo}";
        }
        if (candidatoRepository.findByRg(candidato.getRg()) != null) {
            attributes.addFlashAttribute("mensagem", "RG duplicado!");
            return "redirect:/{codigo}";
        }
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        candidato.setVaga(vaga);
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adicionado com sucesso!");
        return "redirect:/{codigo}";
    }

    @RequestMapping(value="deletarVaga")
    public String deletarVaga(long codigo) {
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        vagaRepository.delete(vaga);
        return "redirect:/vagas";
    }

    // a implementar: "deleta candidato"
}
