package theHexagon;

import java.util.Arrays;
import java.util.Random;

public class Methods {

    private static int[][] matrizP;    
    private static int[] solucaoInicialP;
    private static int[] solucaoSubidaDeEncostaP;
    private static int[] solucaoSubidaDeEAlteradaP;
    private static int[] solucaoTemperaSimuladaP;
    private static int custoInicialP;
    private static int custoSubidaDeEncostaP;
    private static int custoSubidaDeEAlteradaP;
    private static int custoTemperaSimuladaP;
    private static double ganhoComSubidaDeEncostaP;
    private static double ganhoComSubidaDeEncostaAlteradaP;
    private static double ganhoComTemperaSimuladaP;

    public static int[][] retorneMatriz(){
        return matrizP;
    }

    public static int[] retorneSolucaoInicial(){
        return solucaoInicialP;
    }

    public static int[] solucaoSubidaDeEncostaP(){
        return solucaoSubidaDeEncostaP;
    }

    public static int[] solucaoSubidaDeEncostaAlteradaP(){
        return solucaoSubidaDeEAlteradaP;
    }

    public static int[] solucaoTemperaSimuladaP(){
        return solucaoTemperaSimuladaP;
    }

    public static int custoInicialP(){
        return custoInicialP;
    }

    public static int custoSubidaDeEncostaP(){
        return custoSubidaDeEncostaP;
    }

    public static int custoSubidaDeEAlteradaP(){
        return custoSubidaDeEAlteradaP;
    }

    public static int custoTemperaSimuladaP(){
        return custoTemperaSimuladaP;
    }

    public static double ganhoComSubidaDeEncostaP(){
        return ganhoComSubidaDeEncostaP;
    }

    public static double ganhoComSubidaDeEncostaAlteradaP(){
        return ganhoComSubidaDeEncostaAlteradaP;
    }

    public static double ganhoComTemperaSimuladaP(){
        return ganhoComTemperaSimuladaP;
    }

    // MÉTODO PRINCIPAL
    public static void start(int qntd) {
        // CONFIGURAÇÃO DO PROBLEMA
        final int n = 6;
        final int minimo = 20;
        final int maximo = 50;

        // GERA PROBLEMA - MATRIZ DE ADJACÊNCIAS
        int[][] matriz = geraAmbiente(minimo, maximo, n);
        matrizP = matriz;
        realizarMetodos(matriz, qntd, n);
    }

    public static void realizarMetodos(int[][]matriz, int qntd, int n){
        int[][] matrizUsada; 

        if(matriz == null){
            matrizUsada =  geraAmbiente(20, 50, 6);
        } else {
            matrizUsada = matriz;
        }

        int qt = qntd;
        double ga1 = 0;
        double ga2 = 0;
        double ga3 = 0;

        // GERA SOLUÇÃO INICIAL ALEATÓRIA
        int[] si = solucaoInicial(n);
        solucaoInicialP = si;

        // AVALIA SOLUÇÃO INICIAL
        int vi = avaliaSolucao(si, matrizUsada, n);
        custoInicialP = vi;

        // EXECUTA - SUBIDA DE ENCOSTA
        int[] sf = encosta(si, vi, matrizUsada, n);
        ga1 += (double) (vi - avaliaSolucao(sf, matriz, n)) / vi;
        solucaoSubidaDeEncostaP = sf;

        // EXECUTA - SUBIDA DE ENCOSTA ALTERADA
        int tmax = n - 1;
        int[] sfAlt = encostaAlt(si, vi, matrizUsada, n, tmax);
        ga2 += (double) (vi - avaliaSolucao(sf, matrizUsada, n)) / vi;
        solucaoSubidaDeEAlteradaP = sfAlt;

        // EXECUTA - TEMPERA SIMULADA
        double tIni = 800;
        double tFim = 0.01;
        double ftRed = 0.95;
        int[] sfTemp = tempera(si, vi, matrizUsada, tIni, tFim, ftRed);
        ga3 += (double) (vi - avaliaSolucao(sfTemp, matrizUsada, n)) / vi;
        solucaoTemperaSimuladaP = sfTemp;        

        ganhoComSubidaDeEncostaP = 100 * ga1 / qt;
        ganhoComSubidaDeEncostaAlteradaP = 100 * ga2 / qt;
        ganhoComTemperaSimuladaP = 100 * ga3 / qt;
    }

    public static String realizarMetodosParaTabela(int tentativa, 
                                                    double tempInicial1, double tempFinal1, double fatorRed1,
                                                    double tempInicial2, double tempFinal2, double fatorRed2,
                                                    double tempInicial3, double tempFinal3, double fatorRed3,
                                                    double tempInicial4, double tempFinal4, double fatorRed4,
                                                    double tempInicial5, double tempFinal5, double fatorRed5){

        int[][] matrizUsada = geraAmbiente(20, 50, 6); 
        int n = 6;

        double ga1 = 0;
        double ga2 = 0;
        double ga3 = 0;
        double ga4 = 0;
        double ga5 = 0;
        double ga6 = 0;
        double ga7 = 0;
        double ga8 = 0;
        double ga9 = 0;

        // GERA SOLUÇÃO INICIAL ALEATÓRIA
        int[] si = solucaoInicial(n);
        solucaoInicialP = si;

        // AVALIA SOLUÇÃO INICIAL
        int vi = avaliaSolucao(si, matrizUsada, n);
        custoInicialP = vi;

        // EXECUTA - SUBIDA DE ENCOSTA
        int[] sf = encosta(si, vi, matrizUsada, n);
        ga1 += (double) (vi - avaliaSolucao(sf, matrizUsada, n)) / vi;
        solucaoSubidaDeEncostaP = sf;

        // EXECUTA - SUBIDA DE ENCOSTA ALTERADA
        int tmax = tentativa;
        int[] sfAlt = encostaAlt(si, vi, matrizUsada, n, tmax);
        ga2 += (double) (vi - avaliaSolucao(sfAlt, matrizUsada, n)) / vi;
        solucaoSubidaDeEAlteradaP = sfAlt;
        
        int tmax2 = tentativa / 2;
        int[] sfAlt2 = encostaAlt(si, vi, matrizUsada, n, tmax2);
        ga3 += (double) (vi - avaliaSolucao(sfAlt2, matrizUsada, n)) / vi;
        solucaoSubidaDeEAlteradaP = sfAlt;

        int tmax3 = tentativa / 4;
        int[] sfAlt3 = encostaAlt(si, vi, matrizUsada, n, tmax3);
        ga4 += (double) (vi - avaliaSolucao(sfAlt3, matrizUsada, n)) / vi;
        solucaoSubidaDeEAlteradaP = sfAlt;

        // EXECUTA - TEMPERA SIMULADA
        int[] sfTemp = tempera(si, vi, matrizUsada, tempInicial1, tempFinal1, fatorRed1);
        ga5 += (double) (vi - avaliaSolucao(sfTemp, matrizUsada, n)) / vi;
        solucaoTemperaSimuladaP = sfTemp;     
           
        // EXECUTA - TEMPERA SIMULADA
        int[] sfTemp2 = tempera(si, vi, matrizUsada, tempInicial2, tempFinal2, fatorRed2);
        ga6 += (double) (vi - avaliaSolucao(sfTemp2, matrizUsada, n)) / vi;
        solucaoTemperaSimuladaP = sfTemp; 

        // EXECUTA - TEMPERA SIMULADA
        int[] sfTemp3 = tempera(si, vi, matrizUsada, tempInicial3, tempFinal3, fatorRed3);
        ga7 += (double) (vi - avaliaSolucao(sfTemp3, matrizUsada, n)) / vi;
        solucaoTemperaSimuladaP = sfTemp;

        // EXECUTA - TEMPERA SIMULADA
        int[] sfTemp4 = tempera(si, vi, matrizUsada, tempInicial4, tempFinal4, fatorRed4);
        ga8 += (double) (vi - avaliaSolucao(sfTemp4, matrizUsada, n)) / vi;
        solucaoTemperaSimuladaP = sfTemp;    
         
        // EXECUTA - TEMPERA SIMULADA
        int[] sfTemp5 = tempera(si, vi, matrizUsada, tempInicial5, tempFinal5, fatorRed5);
        ga9 += (double) (vi - avaliaSolucao(sfTemp5, matrizUsada, n)) / vi;
        solucaoTemperaSimuladaP = sfTemp;     


        double ganhoComSubidaDeEncostaPK = 100 * ga1;
        double ganhoComSubidaDeEncostaAlteradaPK = 100 * ga2;
        double ganhoComSubidaDeEncostaAlteradaPK2 = 100 * ga3;
        double ganhoComSubidaDeEncostaAlteradaPK3 = 100 * ga4;
        double ganhoComTemperaSimuladaPK = 100 * ga5;
        double ganhoComTemperaSimuladaPK2 = 100 * ga6;
        double ganhoComTemperaSimuladaPK3 = 100 * ga7;
        double ganhoComTemperaSimuladaPK4 = 100 * ga8;
        double ganhoComTemperaSimuladaPK5 = 100 * ga9;

        String registro = String.valueOf(ganhoComSubidaDeEncostaPK) + "," +
                            String.valueOf(ganhoComSubidaDeEncostaAlteradaPK) + "," +
                            String.valueOf(ganhoComSubidaDeEncostaAlteradaPK2) + "," +
                            String.valueOf(ganhoComSubidaDeEncostaAlteradaPK3) + "," +
                            String.valueOf(ganhoComTemperaSimuladaPK) + "," +
                            String.valueOf(ganhoComTemperaSimuladaPK2) + "," +
                            String.valueOf(ganhoComTemperaSimuladaPK3) + "," +
                            String.valueOf(ganhoComTemperaSimuladaPK4) + "," +
                            String.valueOf(ganhoComTemperaSimuladaPK5);
        
        return registro;
    }

    // MÉTODO PARA GERAR MATRIZ DE ADJACÊNCIAS
    public static int[][] geraAmbiente(int minimo, int maximo, int n) {
        int[][] mat = new int[n][n];

        Random rd = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = rd.nextInt(maximo - minimo + 1) + minimo;
                }
            }
        }

        return mat;
    }

    // MÉTODO PARA GERAR UMA SOLUÇÃO INICIAL ALEATÓRIA
    public static int[] solucaoInicial(int n) {
        int[] s = new int[n];

        for (int i = 0; i < n; i++) {
            s[i] = i;
        }

        Random rd = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = rd.nextInt(i + 1);

            int temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        return s;
    }

    // MÉTODO PARA AVALIAR UMA DADA SOLUÇÃO
    public static int avaliaSolucao(int[] s, int[][] mat, int n) {
        int custo = 0;

        for (int i = 0; i < n - 1; i++) {
            custo += mat[s[i]][s[i + 1]];
        }

        custo += mat[s[n - 1]][s[0]];

        return custo;
    }

    // MÉTODO PARA GERAR SUCESSORES PARA SUBIDA DE ENCOSTA
    public static int[] sucessoresEnc(int[] s, int v, int[][] mat) {
        int[] melhor = Arrays.copyOf(s, s.length);
        int vm = v;

        Random rd = new Random();
        int ind = rd.nextInt(s.length);

        for (int i = 0; i < s.length; i++) {
            int[] suc = Arrays.copyOf(s, s.length);
            int aux = suc[ind];
            suc[ind] = suc[i];
            suc[i] = aux;

            int vs = avaliaSolucao(suc, mat, s.length);

            if (vs < vm) {
                melhor = Arrays.copyOf(suc, suc.length);
                vm = vs;
            }
        }

        return melhor;
    }

    // MÉTODO PARA GERAR SUCESSORES PARA TEMPERA SIMULADA
    public static int[] sucessoresTemp(int[] s, int v, int[][] mat) {
        int[] suc = Arrays.copyOf(s, s.length);

        Random rd = new Random();
        int ind1 = rd.nextInt(s.length);
        int ind2 = rd.nextInt(s.length);

        int aux = suc[ind1];
        suc[ind1] = suc[ind2];
        suc[ind2] = aux;

        //int vs = avaliaSolucao(suc, mat, s.length);

        return suc;
    }

    // MÉTODO SUBIDA DE ENCOSTA
    public static int[] encosta(int[] si, int vi, int[][] matriz, int n) {
        int[] atual = Arrays.copyOf(si, si.length);
        int va = vi;

        while (true) {
            int[] novo = sucessoresEnc(atual, va, matriz);
            int vn = avaliaSolucao(novo, matriz, n);

            if (vn < va) {
                atual = Arrays.copyOf(novo, novo.length);
                va = vn;
                custoSubidaDeEncostaP = va;
            } else {
                return atual;
            }
        }
    }

    // MÉTODO SUBIDA DE ENCOSTA ALTERADA
    public static int[] encostaAlt(int[] s, int v, int[][] matriz, int n, int tmax) {
        int[] atual = Arrays.copyOf(s, s.length);
        int va = v;
        int t = 1;

        while (true) {
            int[] novo = sucessoresEnc(atual, va, matriz);
            int vn = avaliaSolucao(novo, matriz, n);

            if (vn < va) {
                atual = Arrays.copyOf(novo, novo.length);
                va = vn;
                custoSubidaDeEAlteradaP = va;
                t = 1;
            } else {
                if (t < tmax) {
                    t++;
                } else {
                    return atual;
                }
            }
        }
    }

    // MÉTODO TEMPERA SIMULADA
    public static int[] tempera(int[] si, int vi, int[][] matriz, double ti, double tf, double fr) {
        int[] atual = Arrays.copyOf(si, si.length);
        int va = vi;
        double temp = ti;

        while (temp > tf) {
            int[] novo = sucessoresTemp(atual, va, matriz);
            int de = avaliaSolucao(novo, matriz, si.length) - va;

            if (de < 0) {
                atual = Arrays.copyOf(novo, novo.length);
                va = avaliaSolucao(atual, matriz, si.length);
            } else {
                double ale = Math.random();
                double aux = Math.exp(-de / temp);

                if (ale <= aux) {
                    atual = Arrays.copyOf(novo, novo.length);
                    va = avaliaSolucao(atual, matriz, si.length);
                    custoTemperaSimuladaP = va;
                }
            }

            temp *= fr;
        }

        return atual;
    }    

}
