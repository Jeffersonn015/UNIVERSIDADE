package Etapa05;

public class Aluno {
    private String nome;  // Nome do aluno
    private String ra;   
    private double[] notas; // guardar as notas do aluno
    private int quantidadeAvaliacoes; // Conta quantas notas o aluno tem
    private boolean ead;  // Diz se a disciplina é EAD ou não

    // Construtor 
    public Aluno(String nome, String ra, boolean ead) {
        this.nome = nome; // Atribui o nome do aluno
        this.ra = ra;     // Atribui o RA do aluno
        this.ead = ead;   // Define se é EAD
        this.notas = new double[4]; // Prepara espaço para até 4 notas
        this.quantidadeAvaliacoes = 0; // Começa com 0 notas
    }

    // Método para adicionar uma nota ao aluno
    public void adicionarNota(double nota) {
        if (quantidadeAvaliacoes < 4) { // Se ainda não atingiu o limite de notas
            notas[quantidadeAvaliacoes] = nota; // Adiciona a nova nota
            quantidadeAvaliacoes++; // Aumenta a contagem de notas
        } else {
            System.out.println("Limite de notas atingido. Não dá pra adicionar mais!"); // Mensagem de erro se já tiver mais de 4 notas
        }
    }

    // Método que calcula a nota final do aluno
    public double calcularNotaFinal() {
        double notaFinal = 0; 

        switch (quantidadeAvaliacoes) { // verifics quantas notas foram adicionadas
            case 1:
            case 2:
                // Se tem até 2 notas, faz a média aritmética
                for (int i = 0; i < quantidadeAvaliacoes; i++) {
                    notaFinal += notas[i]; // Soma todas as notas
                }
                notaFinal /= quantidadeAvaliacoes; // Divide pela quantidade de notas 
                break;

            case 3:
                // Se tem 3 notas, faz a média ponderada
                notaFinal = (notas[0] + 2 * notas[1] + 2 * notas[2]) / 5; // Calcula a média ponderada
                break;

            case 4:
                // Se tem 4 notas, calcula com a fórmula específica
                notaFinal = (notas[0] * 0.15) + (notas[1] * 0.30) + (notas[2] * 0.10) + (notas[3] * 0.45);
                break;

            default:
                System.out.println("Nenhuma nota foi registrada. Não dá pra calcular!"); // Se não tiver notas, avisa
                break;
        }

        return notaFinal; // Retorna a nota final calculada
    }

    // Método que verifica se o aluno está aprovado ou reprovado
    public String verificarSituacao(double presenca) {
        double notaFinal = calcularNotaFinal(); // Calcula a nota final primeiro
        if (ead) {
            // Se for EAD, só olha a nota
            return notaFinal >= 5 ? "Aprovado" : "Reprovado"; // Se a nota for 5 ou mais, aprovado
        } else {
            // Se for presencial, verifica também a presença
            return (notaFinal >= 5 && presenca >= 75) ? "Aprovado" : "Reprovado"; // Aprovado se a nota for boa e presença for 75% ou mais
        }
    }

    // Método que imprime as informações do aluno
    public void imprimirInformacoes(double presenca) {
        double notaFinal = calcularNotaFinal(); // Calcula a nota final
        String situacao = verificarSituacao(presenca); // Verifica se está aprovado ou reprovado
        String tipoEAD = ead ? "Sim" : "Não"; // Define se é EAD ou não
        System.out.println("Nome: " + nome); // Mostra o nome do aluno
        System.out.println("RA: " + ra); // Mostra o RA do aluno
        System.out.println("Nota Final: " + notaFinal); // Mostra a nota final
        System.out.println("Situação: " + situacao); // Mostra se o aluno está aprovado ou reprovado
        System.out.println("É EAD? " + tipoEAD); // Mostra se a disciplina é EAD
    }

    // Método principal para rodar o código
    public static void main(String[] args) {
        Aluno aluno = new Aluno("João da Silva", "249279", false); 
        aluno.adicionarNota(10); // Adiciona a primeira nota
        aluno.adicionarNota(9); // Adiciona a segunda nota
        aluno.adicionarNota(7); // Adiciona a terceira nota
        aluno.adicionarNota(5); // Adiciona a quarta nota

        // Chama o método para imprimir as informações do aluno, considerando uma presença de 80%
        aluno.imprimirInformacoes(80); // Mostra tudo sobre o aluno
    }
}
