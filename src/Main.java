import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final double diariaStandard = 99.90;
    static final double diariaDeluxe = 165.35;
    static final double diariaSuite = 286.57;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int maximoDeReservas = 10;
        int contadorReserva = 0;
        int opcao;


        Reserva[] reservasConfirmadas = new Reserva[maximoDeReservas];

        do {
            chamaMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            contadorReserva = executaOpcao(opcao, reservasConfirmadas, contadorReserva, scanner);

        } while (opcao != 0);
    }

    public static void chamaMenu() {
        System.out.println("\n----------BEM VINDO----------");
        System.out.println("1 - Cadastrar nova reserva \n2 - Listar reservas cadastradas " +
                "\n3 - Buscar reserva por nome do hóspede \n4 - Listar reservas por números de dias \n0 - Sair");
    }

    public static int executaOpcao(int opcao, Reserva[] reservasConfirmadas, int contadorReserva, Scanner scanner) {

        switch (opcao) {
            case 1:
                contadorReserva = cadastraReservaMenu(contadorReserva, reservasConfirmadas, scanner);
                break;
            case 2:
                listaReservas(reservasConfirmadas, contadorReserva);
                break;
            case 3:
                buscaReservaPorNome(reservasConfirmadas, contadorReserva, scanner);
                break;
            case 4:
                listaReservasDecrescente(reservasConfirmadas, contadorReserva);
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!\n");
        }
        return contadorReserva;
    }

    private static void listaReservasDecrescente(Reserva[] reservasConfirmadas, int contadorReserva) {
        if (contadorReserva == 0) {
            System.out.println("\nNenhuma reserva cadastrada.");
        } else {
            Reserva[] reservasDecrescente = Arrays.copyOf(reservasConfirmadas, contadorReserva);

            Arrays.sort(reservasDecrescente, (r1, r2) ->
                    Integer.compare(r2.getQuantidadeDiarias(), r1.getQuantidadeDiarias()));
            System.out.println("\nLista de reservas ordenadas por quantidade de diárias (decrescente): ");
            for (Reserva reserva : reservasDecrescente)
                System.out.println(reserva);
        }
    }

    private static void buscaReservaPorNome(Reserva[] reservasConfirmadas, int contadorReserva, Scanner scanner) {
        System.out.println("\nDigite o nome do hóspede que deseja buscar a reserva: ");
        String nomeDesejado = scanner.nextLine().toUpperCase();

        int reservasEncontradas = 0;

        for (int i = 0; i < contadorReserva; i++) {
            if (reservasConfirmadas[i] != null && reservasConfirmadas[i].getNomeHospede().contains(nomeDesejado)) {
                System.out.println(reservasConfirmadas[i]);
                reservasEncontradas++;
            }
        }
        if (reservasEncontradas == 0) {
            System.out.println("\nNenhuma reserva com o nome " + nomeDesejado + " encontrada");
        } else {
            System.out.println("\nForam encontradas " + reservasEncontradas + " reservas para o hóspede " + nomeDesejado);
        }
    }

    private static void listaReservas(Reserva[] reservasConfirmadas, int contadorReserva) {
        if (contadorReserva == 0) {
            System.out.println("\nNenhuma reserva cadastrada.");
        } else {
            System.out.println("\nLista de reservas confirmadas: ");
            for (int i = 0; i < contadorReserva; i++) {
                System.out.println(reservasConfirmadas[i]);
            }
        }
    }

    public static int cadastraReservaMenu(int contadorReservas, Reserva[] reservasConfirmadas, Scanner scanner) {
        if (contadorReservas < reservasConfirmadas.length) {
            Reserva nova = cadastraReserva(scanner);
            if (nova != null) {
                reservasConfirmadas[contadorReservas] = nova;
                contadorReservas++;
                System.out.println("Reserva cadastrada com sucesso!");
            }
        } else {
            System.out.println("Limite máximo de reservas atingidos, não é possível mais cadastrar reservas.\n");
        }
        return contadorReservas;
    }

    private static Reserva cadastraReserva(Scanner scanner) {

        System.out.println("Digite o nome completo do hóspede: ");
        String nome = scanner.nextLine().toUpperCase();

        chamaMenuQuartos();
        int opcaoQuarto = scanner.nextInt();
        scanner.nextLine();
        String tipoQuarto = executaOpcaoMenuQuartos(opcaoQuarto, scanner);

        System.out.println("Digite a quantiade de diárias desejadas: ");
        int quantidadeDiarias = scanner.nextInt();

        double valorDiaria;
        if (tipoQuarto.equals("Standard")) {
            valorDiaria = diariaStandard;
        } else if (tipoQuarto.equals("Deluxe")) {
            valorDiaria = diariaDeluxe;
        } else {
            valorDiaria = diariaSuite;
        }
        Reserva novaReserva = new Reserva(nome, tipoQuarto, quantidadeDiarias, valorDiaria);

        System.out.println("Informaçoes da reserva: " + novaReserva + "\n\nDeseja confirmar a reserva acima?");
        System.out.println("1 - Confirmar reserva \n2 - Cancelar reserva");
        int opcaoConfirmaReserva = scanner.nextInt();
        scanner.nextLine();

        if (opcaoConfirmaReserva != 1) {
            System.out.println("Cadastro de reserva Cancelado.\n");
            return null;
        }
        return novaReserva;
    }

    public static void chamaMenuQuartos() {

        System.out.println("Escolha o quarto desejado: ");
        System.out.println("1 - Standard R$" + diariaStandard + "\n2 - Deluxe R$ " + diariaDeluxe + "\n3 - Suite R$ " + diariaSuite);
    }

    public static String executaOpcaoMenuQuartos(int opcaoQuarto, Scanner scanner) {
        String quartoEscolhido = "";

        while (true) {
            switch (opcaoQuarto) {
                case 1:
                    quartoEscolhido = "Standard";
                    System.out.println("O quarto escolhido foi " + quartoEscolhido);
                    return quartoEscolhido;
                case 2:
                    quartoEscolhido = "Deluxe";
                    System.out.println("O quarto escolhido foi " + quartoEscolhido);
                    return quartoEscolhido;
                case 3:
                    quartoEscolhido = "Suite";
                    System.out.println("O quarto escolhido foi " + quartoEscolhido);
                    return quartoEscolhido;
                default:
                    System.out.println("Opção inválida!");
                    chamaMenuQuartos();
                    opcaoQuarto = scanner.nextInt();
                    scanner.nextLine();
            }
        }
    }
}
