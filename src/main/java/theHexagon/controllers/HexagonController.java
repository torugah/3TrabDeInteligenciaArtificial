package theHexagon.controllers;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import theHexagon.Methods;

@RestController
@RequestMapping(value = "/program")
@CrossOrigin(origins = {"http://localhost:8080",
                        "http://localhost:5500",
                        "http://127.0.0.1:5500/index.html",
                        "http://127.0.0.1:5500"}, allowedHeaders = {"Authorization"})

public class HexagonController {

    @GetMapping(value = "/generateMatriz")
    public int[][] retornaMatriz(@RequestParam(name = "qntd", defaultValue = "1") int qntd){
        Methods.start(qntd);
        return Methods.retorneMatriz();
    }

    /*
    Teste:
    http://localhost:8080/program/returnTable/12/800/0.05/0.85/825/0.01/0.87/850/0.04/0.89/875/0.03/0.91/900/0.02/0.93
    */
    @GetMapping(value = "/returnTable/{tentativa}/{ti1}/{tf1}/{fr1}/{ti2}/{tf2}/{fr2}/{ti3}/{tf3}/{fr3}/{ti4}/{tf4}/{fr4}/{ti5}/{tf5}/{fr5}")
    public ResponseEntity<String> retornaTabela(
        @PathVariable int tentativa,
        @PathVariable double ti1,
        @PathVariable double tf1,
        @PathVariable double fr1,
        @PathVariable double ti2,
        @PathVariable double tf2,
        @PathVariable double fr2,
        @PathVariable double ti3,
        @PathVariable double tf3,
        @PathVariable double fr3,
        @PathVariable double ti4,
        @PathVariable double tf4,
        @PathVariable double fr4,
        @PathVariable double ti5,
        @PathVariable double tf5,
        @PathVariable double fr5
    ){
        String table = Methods.realizarMetodosParaTabela(tentativa, ti1, tf1, fr1,
                                                            ti2, tf2, fr2,
                                                            ti3, tf3, fr3, 
                                                            ti4, tf4, fr4, 
                                                            ti5, tf5, fr5);
        return ResponseEntity.ok(table);
    }

    @GetMapping(value = "/returnPercentege", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> retorneOsGanhos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        String g1 = df.format(Methods.ganhoComSubidaDeEncostaP());
        String g2 = df.format(Methods.ganhoComSubidaDeEncostaAlteradaP());
        String g3 = df.format(Methods.ganhoComTemperaSimuladaP());

        Map<String, String> response = new HashMap<>();
        response.put("Ganhos Com Subida De Encosta", g1);
        response.put("Ganhos Com Subida De Encosta Alterada", g2);
        response.put("Ganhos Com Tempera Simulada", g3);

        return ResponseEntity.ok(response);
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

    @GetMapping(value = "/returnCustoInicial")
    public int returnCustoInicial(){
        return Methods.custoInicialP();
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