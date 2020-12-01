package edu.usj.crudfinal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LocadorController {
    
    @Autowired
    LocadorRepository locadorRepository;

    @GetMapping(value="/")
    public ModelAndView readListalocadores() {
        List<Locador> listalocadores = locadorRepository.findAll();
        
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listalocadores", listalocadores);
        return modelAndView;
    }

    @GetMapping(value="/detalhes/{id}")
    public ModelAndView readlocadorById(@PathVariable Long id) {

        //ler do banco
        Locador locador = locadorRepository.findById(id).get();       
        
        //acrescentar na modelAndView
        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("locador", locador);
        return modelAndView;

    }

    @GetMapping(value="/cadastro")
    public ModelAndView createlocador() {
        Locador locador = new Locador();
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("locador", locador);
        return modelAndView;
    }


    @PostMapping(value="/adicionar")
    public String adicionarlocador(Locador locador) {
        
        locadorRepository.save(locador);
        return "redirect:/";
        
        // List<locador> listalocadores = locadorRepository.findAll();

        // ModelAndView modelAndView = new ModelAndView("index");
        // modelAndView.addObject("listalocadores", listalocadores);
        // return modelAndView;       
    }
    

    @GetMapping(value="/deletar/{id}")
    public String deletelocadorById(@PathVariable Long id) {
        locadorRepository.deleteById(id);             
        return "redirect:/";  
    }


    @GetMapping(value="/editar/{id}")
    public ModelAndView updatelocadorById(@PathVariable Long id) {
        Locador locador = locadorRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("locador", locador);
        return modelAndView;
    }

    @GetMapping(value="/pesquisar")
    public String getSelectlocador() {
        return "pesquisar";
    }

    @PostMapping(value="/pesquisar")
    public ModelAndView postSelectlocador(@RequestParam String nome) {
        List<Locador> listalocadores = locadorRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);

        ModelAndView modelAndView = new ModelAndView("pesquisar");
        modelAndView.addObject("nome", nome);
        modelAndView.addObject("listalocadores", listalocadores);
        return modelAndView;
    }
}
 
    
