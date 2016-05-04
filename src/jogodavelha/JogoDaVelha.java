/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import javax.swing.JOptionPane;

/**
 *Programa com a utilização do algoritmo minMax e
 * minMax com poda alpha betha para implementação
 * do jogo da velha
 * @author Alisson Rangel Alves Escórcio de Carvalho
 */
public class JogoDaVelha {

    public static Janela_J_V janela = new Janela_J_V();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        janela.setVisible(true);
    }
    
    public static int[][] minMax(Estado estado){
        
        Estado estadoResposta = new Estado();
        
        //ok codigo minMax
        //estadoResposta = valorMaxAux0(estado);
        
        //ok codigo minMax com alphaBetha
        estadoResposta = valorMaxAlphaBetha_Aux(estado, -2, 2);
        
        System.out.println("Fim do minMax valor estado statgus: "+estado.status);
        
        //Matriz da resposta encontrada
        for (int l = 0; l < 3; l++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(estadoResposta.matriz[l][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        return estadoResposta.matriz;
    }
    
    public static int testeTerminal(Estado estado, int status){        
        int[][] m = new int[3][3];
        m = estado.matriz;
        
        int l = 0;
        //diagonal secundáia
        for (int i = 2; i >= 0; i--) {
            if ( m[l++][i] == status ){
                    
                    if (i == 0){
                        return status == 1? 1 : -1;
                    }
                } else {
                    i = -1;
                }
        }
        
        //diagonal principal
        for (int i = 0; i < 3; i++) {
            if ( m[i][i] == status ){
                    if (i == 2){
                        return status == 1? 1 : -1;
                    }
                } else {
                    i = 3;
                }
        }
        //linhas 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( m[i][j] == status ){
                    if (j == 2){
                        return status == 1? 1 : -1;
                    }
                } else {
                    j = 3;
                }
                
            }
        }
        
        //colunas 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( m[j][i] == status ){
                    if (j == 2){
                        return status == 1? 1 : -1;
                    }
                } else {
                    j = 3;
                }
                
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( m[i][j] == -1){
                    return -2; //se ha jogadas possiveis
                }
            }
        }
        return 0; //caso de empate
    }
    
    public static Estado valorMaxAlphaBetha_Aux(Estado estado, int alpha, int betha){
        int respostaTerminal;               
        int count = 0;      
        
        respostaTerminal = testeTerminal(estado, 0);     
        
        
        if ( respostaTerminal != -2 ){
        
            estado.valor = respostaTerminal;
            return estado;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( estado.matriz[i][j] == -1 ) {   
                    count++;
                } 
            }
        }
        /**
         * Bloco que cria os estados possíveis de serem criados
         */
        Estado estados[] = new Estado[count]; 

        estados = criarEstado(estado, count, 1);
        for (int i = 0; i < count; i++) {
            System.out.println("status: "+estados[i].status);
        }
        //valor -infinito
        int valor = -2;
        int valorAux = 0;
        
        //estado que receberá o retorno
        Estado estadoRetornado = estados[0];
        
        //teste dos sucessores
        for (int i = 0; i < count; i++) {
            
            
            valorAux = valorMinAlphaBetha(estados[i], alpha, betha);
            if (valorAux > valor){
                valor = valorAux;
                estadoRetornado = estados[i];
            }
            if ( valor >= betha ){
                return estadoRetornado;
            }
        
            alpha = Integer.max(alpha, valor);
        }
        
        //retorno do estado com a matriz da próxima jogada
        return estadoRetornado;
    }
    
    public static int valorMaxAlphaBetha(Estado estado, int alpha, int betha){
        int respostaTerminal;        
        int count = 0;
        
        
        respostaTerminal = testeTerminal(estado, 0);     
        
        
        if ( respostaTerminal != -2 ){
            return respostaTerminal;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( estado.matriz[i][j] == -1 ) {
                    count++;
                } 
            }
        }
        /**
         * Bloco que cria os estados possíveis de serem criados
         */
        Estado estados[] = new Estado[count];
        
        estados = criarEstado(estado, count, 1);
        int valor = -2;
        int valorAux = -2;
        
        //teste dos sucessores
        for (int i = 0; i < count; i++) {
            
            valorAux = valorMinAlphaBetha(estados[i], alpha, betha);
            //valor = Integer.max(valor, estado1.valor);
            if ( valorAux > valor ){
                valor = valorAux;
            }
            if ( valor >= betha ){
                
                return valor;
            }
        
            alpha = Integer.max(alpha, valor);
        }
        
        //retorno do valor max
        return valor;
    }
    
    public static int valorMinAlphaBetha(Estado estado, int alpha, int betha){
        
        int respostaTerminal;      
        int count = 0;
        
        respostaTerminal = testeTerminal(estado, 1);     
        
        
        if ( respostaTerminal != -2 ){
           
            return respostaTerminal;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( estado.matriz[i][j] == -1 ) {   
                    count++;
                } 
            }
        }
        
        /**
         * Bloco que cria os estados possíveis de serem criados
         */
        Estado estados[] = new Estado[count];
        
        estados = criarEstado(estado, count, 0);
        
        int valor = 2;
        int valorAux;
        
        //teste dos sucessores
        for (int i = 0; i < count; i++) {           
            
            valorAux = valorMaxAlphaBetha(estados[i], alpha, betha);
            
            if ( valorAux < valor  ){
                valor = valorAux;
            }
            
            if ( valor <= alpha ){
                return valor;
            }
        
            betha = Integer.min(betha, valor);
       
        }
        
        //retorno do valor min
        return valor;
        
    }
    
    public static Estado valorMaxAux0(Estado estado){
        int respostaTerminal;        
        
        boolean flag = true;
        int count = 0;
        
        respostaTerminal = testeTerminal(estado, 0);
        
        if ( respostaTerminal != -2 ){
            estado.valor = respostaTerminal;
            return estado;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( estado.matriz[i][j] == -1 ) {   
                    flag = false;
                    count++;
                } 
            }
        }
        /**
         * Bloco que cria os estados possíveis de serem criados
         */
        Estado estados[] = new Estado[count];
               
        estados = criarEstado(estado, count, 1);
        int valor = -2;
        int[][] m = new int[3][3];
        
        for (int k = 0; k < count; k++) {
            m = estados[k].matriz;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(m[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        Estado estadoAux;
        Estado estadoRetornado = estados[0];
        int valorRetornado = -2;
        int venc = -1;
        for (int i = 0; i < count; i++) {
            
            int valorAux = valorMinAux(estados[i]);
            if ( valorAux > valor ){
                valorRetornado = valorAux;
                valor = valorAux;
                venc = i;
                m = estados[i].matriz;
                estadoRetornado = new Estado(m);
                
            }
        }
        

        /**
         * retorna um novo estado da próxima mexida;
         */
        return estadoRetornado;
    }
    
    public static int valorMaxAux(Estado estado){
        int respostaTerminal;        
        
        boolean flag = true;
        int count = 0;
        
        respostaTerminal = testeTerminal(estado, 0);
        
        if ( respostaTerminal != -2 ){
            estado.valor = respostaTerminal;
            return estado.valor;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( estado.matriz[i][j] == -1 ) {   
                    flag = false;
                    count++;
                } 
            }
        }
        /**
         * Bloco que cria os estados possíveis de serem criados
         */
        Estado estados[] = new Estado[count];
               
        estados = criarEstado(estado, count, 1);
        int valor = -2;
        
        
        Estado estadoAux;
        Estado estadoRetornado = estados[0];
        int valorRetornado = -2;
        int venc = -1;
        for (int i = 0; i < count; i++) {
            
            int valorAux = valorMinAux(estados[i]);
            
            if ( valorAux > valor ){
                valorRetornado = valorAux;
                valor = valorAux;
                venc = i;
            }
            
        }
        

        /**
         * retorna um novo estado da próxima mexida;
         */
        return valor;
    }
    
    public static int valorMinAux(Estado estado){
        int respostaTerminal;        
        
        boolean flag = true;
        int count = 0;
        
        respostaTerminal = testeTerminal(estado, 1);
        
        if ( respostaTerminal != -2 ){
            estado.valor = respostaTerminal;
            return estado.valor;
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( estado.matriz[i][j] == -1 ) {   
                    flag = false;
                    count++;
                } 
            }
        }
        /**
         * Bloco que cria os estados possíveis de serem criados
         */
        Estado estados[] = new Estado[count];
               
        estados = criarEstado(estado, count, 0);
        int valor = 2;
        
        Estado estadoAux;
        Estado estadoRetornado = estados[0];
        int valorRetornado = -2;
        int venc = -1;
        for (int i = 0; i < count; i++) {
            
            int valorAux = valorMaxAux(estados[i]);
            
            if ( valorAux < valor ){
                valorRetornado = valorAux;
                valor = valorAux;
                venc = i;
                
            }
            
            
        }

        /**
         * retorna um novo estado da próxima mexida;
         */
        return valor;
    }
    
    public static Estado[] criarEstado(Estado estado, int count, int status){
        
        Estado estados[] = new Estado[count];
        int c = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if ( estado.matriz[j][k] == -1 ) {
                    estados[c] = new Estado(estado.matriz);
                    estados[c].matriz[j][k] = status;
                    estados[c].status = status;
                    estados[c].controle = estado.controle;
                    c++;
                }
            }
        
        }   
        return estados;
    }
    
}
