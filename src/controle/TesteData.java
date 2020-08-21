/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author edson
 */
public class TesteData {

    public static int calcularMes(int mes) {

        if (mes < 13) {
            return mes;
        } else {
            return calcularMes(mes - 12);
        }

    }
    
    public static int calcularAno(int mes, int ano) {

        if (mes < 13) {
            return ano;
        } else {
            ano++;
            return calcularAno(mes - 12, ano);
        }

    }
    

    public static String gerarMesVencimento(String dataVenda, int numeroParcela) {

        int dia = Integer.parseInt(dataVenda.substring(0, 2));
        int mes = Integer.parseInt(dataVenda.substring(3, 5));
        int ano = Integer.parseInt(dataVenda.substring(6));
        
        mes += numeroParcela;
        
        int novoMes = calcularMes(mes);
        int novoAno = calcularAno(mes, ano);

        return String.format ("%02d", dia) + "/" + String.format ("%02d", novoMes) + "/" + novoAno + "";

    }

    public static void main(String[] args) {

        String dataVenda = "18/08/2020";

        int parcelas = 36;

        while (parcelas > 0) {
            String data = gerarMesVencimento(dataVenda, parcelas);
            System.out.println(data);
            parcelas--;
        }
    }
}
