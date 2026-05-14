// LivroViewController.java
package com.carbryx.controller;

import com.carbryx.model.Livro;
import com.carbryx.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/view/livros")
@RequiredArgsConstructor
public class LivroViewController {

    private final LivroService livroService;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("livro", livroService.buscarPorId(id));
        return "livros/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Livro livro, RedirectAttributes ra) {
        livroService.salvar(livro);
        ra.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");
        return "redirect:/view/livros";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Livro livro, RedirectAttributes ra) {
        livro.setId(id);
        livroService.atualizar(livro);
        ra.addFlashAttribute("mensagem", "Livro atualizado com sucesso!");
        return "redirect:/view/livros";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes ra) {
        livroService.deletar(id);
        ra.addFlashAttribute("mensagem", "Livro excluído com sucesso!");
        return "redirect:/view/livros";
    }
}