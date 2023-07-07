package Ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //poder criar mais de um ve�culo
        List<Veiculos> cadastro = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao cadastro de ve�culos!");
        System.out.println("Deseja inserir uma moto (M) ou um carro (C)? ");
        String opcao = scanner.nextLine();
        // � ignorado letras maiusculas ou minusculas e o usu�rio escolhe se quer adicionar carro ou moto
        // ap�s escolher, � necess�rio colocar as caracter�sticas
        while (opcao.equalsIgnoreCase("M") || opcao.equalsIgnoreCase("C")) {
            if (opcao.equalsIgnoreCase("M")) {
                Moto moto = new Moto();
                moto.insertData();
                cadastro.add(moto);
            } else if (opcao.equalsIgnoreCase("C")) {
                Carro carro = new Carro();
                carro.insertData();
                cadastro.add(carro);
            }
            // usado para fazer o usu�rio escolher adicionar mais ou sair 
            System.out.print("Deseja inserir uma moto (M) ou um carro (C)? (Digite S para sair) ");
            opcao = scanner.nextLine();

            if (opcao.equalsIgnoreCase("S")) {
                break;
            }
        }
        // for-each, percorre os elementos da lista cadastro
        // cadatro � atribuida a veiculo, � mostrado na tela todos os veiculos cadastrados com o printDados()
        System.out.println("\nRelat�rio de Ve�culos:");
        for (Veiculos veiculo : cadastro) {
            veiculo.printDados();
            System.out.println("-------------------------");
        }

        // determina que o totalPrecosAntes = getTotalPrecos, o valor total de todos os veiculos s�o atribu�dos a variavel nova
        double totalPrecosAntes = getTotalPrecos(cadastro);
        System.out.println("\nTotal de Pre�os dos Ve�culos (Antes do Reajuste): R$" + totalPrecosAntes);

        // aqui ele percorre todos os veiculos cadastrados e verifica com o instanceof se ele � uma moto ou um carro e atribui regras para as duas situa��es
        for (Veiculos veiculo : cadastro) {
            //em seguida ser� feito um cast para moto e ve�culo, um cast permite que tratemos o objeto como uma inst�ncia de Moto ou carro e assim podemos acessar os m�todos e vari�veis espec�ficas dessas classes.
            // o cast s� � v�lido se o objeto for realmente uma inst�ncia do tipo para o qual estamos fazendo o cast, ou uma subclasse desse tipo.
            if (veiculo instanceof Moto) {
                Moto moto = (Moto) veiculo;
                if (moto.getAno() >= 2008) {
                    double novoPreco = moto.getPreco() * 1.1;
                    moto.setPreco(novoPreco);
                }
            } else if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                if (carro.getKm() > 100000) {
                    double novoPreco = carro.getPreco() * 0.92;
                    carro.setPreco(novoPreco);
                }
            }
        }

        double totalPrecosDepois = getTotalPrecos(cadastro);
        System.out.println("\nTotal de Pre�os dos Ve�culos (Ap�s o Reajuste): R$" + totalPrecosDepois);
        //finaliza o uso da classe Scanner e libera buffer de mem�ria
        scanner.close();
    }

    // percorre cada veiculo na lista Veiculos e soma o pre�o de todos, por fim atribui todos os valores na variavel total
    private static double getTotalPrecos(List<Veiculos> veiculos) {
        double total = 0.0;
        for (Veiculos veiculo : veiculos) {
            total += veiculo.getPreco();
        }
        return total;
    }
}