
import java.util.ArrayList;

public class VanHemodialise {
    public static ArrayList<Integer> vanHemo(int[][] matrizDistancias, int pacienteInicial) {
        // Número de pacientes
        int n = matrizDistancias[0].length;
        // ArrayList para armazenar os pacientes visitados
        ArrayList<Integer> visitados = new ArrayList<>();
        // Adiciona o paciente inicial às visitados
        visitados.add(pacienteInicial);

        // Percorre os pacientes fazendo as escolhas
        int pacienteAtual = pacienteInicial;
        while (visitados.size() < n) {
            // Encontra o paciente mais próximo não visitado
            int pacienteProximo = -1;
            int distanciaMenor = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visitados.contains(i) && matrizDistancias[pacienteAtual][i] < distanciaMenor) {
                    pacienteProximo = i;
                    distanciaMenor = matrizDistancias[pacienteAtual][i];
                }
            }
            // Verifica se encontrou um paciente próximo
            if (pacienteProximo != -1) {
                // Adiciona o paciente próxima aos visitados
                visitados.add(pacienteProximo);
                pacienteAtual = pacienteProximo;
            } else {
                // Não encontrou paciente próximo, finaliza o percurso
                break;
            }
        }

        // Adiciona o paciente inicial ao final do trajeto
        visitados.add(pacienteInicial);
        return visitados;
    }

    public static int calcularDistanciaTotal(int[][] matrizDistancias, ArrayList<Integer> pacientesVisitados) {
        int distanciaTotal = 0;
        int pacienteAnterior = pacientesVisitados.get(0);
        for (int i = 1; i < pacientesVisitados.size(); i++) {
            int pacienteAtual = pacientesVisitados.get(i);
            distanciaTotal += matrizDistancias[pacienteAnterior][pacienteAtual];
            pacienteAnterior = pacienteAtual;
        }
        return distanciaTotal;
    }

    public static void main(String[] args) {
        // Matriz de distâncias entre os pacientes, sendo nesse caso usado 7 pacientes e o vertice 0 sendo o hospital, ponto de partida e chegada do percurso.
        // Nesse mesmo programa, na classe GeradorMatriz pode ser criada uma nova matriz de forma aleatória e que satisfaça os requisitos do nosso caso de uso.
        int[][] distancias = {
                {0, 2, 5, 2, 3, 4, 5, 3},
                {2, 0, 3, 5, 2, 5, 2, 3},
                {5, 3, 0, 2, 5, 3, 2, 2},
                {2, 5, 2, 0, 3, 5, 3, 5},
                {3, 2, 5, 3, 0, 2, 5, 2},
                {4, 5, 3, 5, 2, 0, 3, 5},
                {5, 2, 2, 3, 5, 3, 0, 2},
                {3, 3, 2, 5, 2, 5, 2, 0}
        };

        // Nome dos vértices, sendo o primeiro sempre "Hospital" por ser o ponto de partida. Já os demais, os nomes dos pacientes.
        String[] nomesVertices = {"Hospital", "Beto", "Clara", "Daniel", "Eva", "Felipe", "Gabi", "Henrique"};

        // Ponto inicial, que no caso é o Hospital.
        int pacienteInicial = 0;

        // Encontra o trajeto da van
        ArrayList<Integer> trajeto = vanHemo(distancias, pacienteInicial);

        // Calcula a distância total percorrida
        int distanciaTotal = calcularDistanciaTotal(distancias, trajeto);

        // Imprime o trajeto
        System.out.println("Trajeto da van:");
        System.out.println("EX:  3: Felipe(5) -- '3' se refere ao numero da parada e '5' se refere ao vertice referente ao nome do paciente (ou hospital, quando '0').");
        for (int i=0; i < trajeto.size(); i++){
            int paciente = trajeto.get(i);
            String nomePaciente = nomesVertices[paciente];
            if (i == 0){
                System.out.printf("%d(Ponto de partida): %s(%d),  ", i, nomePaciente, paciente);
            } else if (i == trajeto.size()-1){
                System.out.printf("%d(Ponto final): %s(%d).\n", i, nomePaciente, paciente);
            } else{
                System.out.printf("%d: %s(%d),  ", i, nomePaciente, paciente);
            }
        }
        System.out.printf("Distância total percorrida: %d km.", distanciaTotal);
    }
}