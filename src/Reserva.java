public class Reserva {

    private String nomeHospede;
    private String tipoQuarto;
    private int quantidadeDiarias;
    private double totalReserva;

    public Reserva(String nomeHospede, String tipoQuarto, int quantidadeDiarias) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        this.quantidadeDiarias = quantidadeDiarias;
    }

    public double getTotalReserva() {
        return totalReserva;
    }

    public int getQuantidadeDiarias() {
        return quantidadeDiarias;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public void setQuantidadeDiarias(int quantidadeDiarias) {
        this.quantidadeDiarias = quantidadeDiarias;
    }

    public void setTotalReserva(double totalReserva) {
        this.totalReserva = totalReserva;
    }

    public void calculaTotalReserva(double valorDiaria){
        this.totalReserva = this.quantidadeDiarias * valorDiaria;
    }

    @Override
    public String toString() {
        return "\nNome do hospede: " + nomeHospede + "\nTipo do quarto: " + tipoQuarto +
                "\nQuantidade de Diárias: " + quantidadeDiarias +
                "\nValor total da reserva: " + totalReserva;
    }

}
