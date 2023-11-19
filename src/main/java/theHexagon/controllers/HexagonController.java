package theHexagon.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import theHexagon.Methods;

@RestController
@RequestMapping(value = "/program")
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:5500", "http://127.0.0.1:5500"}, allowedHeaders = {"Authorization"})
public class HexagonController {

    @GetMapping(value = "/generateMatriz")
    public int[][] retornaMatriz(){
        Methods.start();
        return Methods.retorneMatriz();
    }

    @GetMapping(value = "/returnInitialSolution")
    public int[] returnInitialSolution(){
        return Methods.retorneSolucaoInicial();
    }

    @GetMapping(value = "/returnSolucaoSubidaDeEncosta")
    public int[] returnSolucaoSubidaDeEncosta(){
        return Methods.solucaoSubidaDeEncostaP();
    }

    @GetMapping(value = "/returnSolucaoSubidaDeEncostaAlterada")
    public int[] returnSolucaoSubidaDeEncostaAlterada(){
        return Methods.solucaoSubidaDeEncostaAlteradaP();
    }

    @GetMapping(value = "/returnSolucaoTemperaSimulada")
    public int[] returnSolucaoTemperaSimulada(){
        return Methods.solucaoTemperaSimuladaP();
    }

    @GetMapping(value = "/returnCustoSubidaDeEncosta")
    public int returnCustooSubidaDeEncosta(){
        return Methods.custoSubidaDeEncostaP();
    }

    @GetMapping(value = "/returnCustoSubidaDeEncostaAlterada")
    public int returnCustooSubidaDeEncostaAlterada(){
        return Methods.custoSubidaDeEAlteradaP();
    }

    @GetMapping(value = "/returnCustoTemperaSimulada")
    public int returnCustooTemperaSimulada(){
        return Methods.custoTemperaSimuladaP();
    }

    @GetMapping("/teste")
        public String getMensagem(@RequestParam(name = "nome", defaultValue = "Visitante") String nome) {
            return "Olá, " + nome + "! Esta é uma mensagem de exemplo.";
        }

}