import java.util.Random;

public class GeradorMatriz {
    public static void main(String[] args) {
        int[][] matriz = new int[8][8];
        Random rand = new Random();

        // Preenche a diagonal principal com zeros e preenche a matriz de forma simétrica
        for (int i = 0; i < matriz.length; i++) {
            for (int j = i + 1; j < matriz[0].length; j++) {
                int distancia = rand.nextInt(4) + 2;
                matriz[i][j] = distancia;
                matriz[j][i] = distancia;
            }
        }

        // Imprime a matriz gerada formatada
        System.out.println("Matriz gerada:");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("{");
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
                if (j != matriz[0].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
    }
}