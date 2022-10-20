package br.com.juwer.algafoodapi;

import br.com.juwer.algafoodapi.di.modelo.Cliente;
import br.com.juwer.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    private AtivacaoClienteService ativadorClienteService;

    public MyController(AtivacaoClienteService ativadorClienteService) {
        this.ativadorClienteService = ativadorClienteService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Cliente bruno = new Cliente("Bruno", "bruno@bruno.com", "51-999999999");


        ativadorClienteService.ativar(bruno);
        return "Hello!";
    }
}
