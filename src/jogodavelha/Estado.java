/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

/**
 *
 * @author Alisson
 */
public class Estado {
    public int matriz[][] = new int[3][3];
    public int valor = 0;
    //public int linha;
    //public int coluna;
    //public int[][] yx = new int[9][2];
    public int controle;
    public int status;
    public Estado(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = -1;
            }
        }
    }
    public Estado(int matriz[][]){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
    }
}
