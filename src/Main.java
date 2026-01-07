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
        System.out.println("----------BEM VINDO----------");
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
                // buscar reserva por nome
                break;
            case 4:
                //listar reserva por número de dias
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!\n");
        }
        return contadorReserva;
    }

    private static void listaReservas(Reserva[] reservasConfirmadas, int contadorReserva) {
        if (contadorReserva == 0 ) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
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
            System.out.println("Limite máximo de reservas atingidos.");
        }
        return contadorReservas;
    }

    private static Reserva cadastraReserva(Scanner scanner) {


        System.out.println("Digite o nome completo do hóspede: ");
        String nome = scanner.nextLine();


        chamaMenuQuartos();
        int opcaoQuarto = scanner.nextInt();
        scanner.nextLine();
        String tipoQuarto = executaOpcaoMenuQuartos(opcaoQuarto);

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
        Reserva novaReserva = new Reserva(nome, tipoQuarto, quantidadeDiarias);
        novaReserva.calculaTotalReserva(valorDiaria);

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

    public static String executaOpcaoMenuQuartos(int opcaoQuarto) {
        String quartoEscolhido = "";
        switch (opcaoQuarto) {
            case 1:
                quartoEscolhido = "Standard";
                System.out.println("O quarto escolhido foi " + quartoEscolhido);
                break;
            case 2:
                quartoEscolhido = "Deluxe";
                System.out.println("O quarto escolhido foi " + quartoEscolhido);
                break;
            case 3:
                quartoEscolhido = "Suite";
                System.out.println("O quarto escolhido foi " + quartoEscolhido);
                break;
            default:
                System.out.println("Opção inválida!");

        }

        return quartoEscolhido;
    }


}
