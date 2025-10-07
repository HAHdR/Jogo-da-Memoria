import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JogoMemoria extends JFrame {
    private ListaLigada cartas;
    private JButton[] botoes;
    private Carta primeiraCarta = null;
    private JButton primeiroBotao = null;
    private int paresEncontrados = 0;

    public JogoMemoria() {
        setTitle("Jogo da Memória");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 6));

        inicializarCartas();
        criarInterface();

        setVisible(true);
    }

    // Inicializa e embaralha as cartas
    private void inicializarCartas() {
        String[] valores = {"A", "G", "D", "F", "B", "G", "C", "B", "F", "D", "H", "H", "I", "I", "A", "E", "C", "E", "J", "J"};
        cartas = new ListaLigada();

        for (String valor : valores) {
            cartas.adicionar(new Carta(valor));
        }

        // Embaralhar usando troca entre índices
        Random rand = new Random();
        int tamanho = cartas.tamanho();
        for (int i = 0; i < tamanho; i++) {
            cartas.trocar(i, rand.nextInt(tamanho));
        }
    }

    // Cria os botões da interface gráfica
    private void criarInterface() {
        int tamanho = cartas.tamanho();
        botoes = new JButton[tamanho];

        for (int i = 0; i < tamanho; i++) {
            JButton botao = new JButton("");
            botao.setFont(new Font("Arial", Font.BOLD, 24));
            botao.setBackground(Color.GREEN); // Cor de fundo quando a carta está oculta
            botao.setOpaque(true); // Para que a cor de fundo seja visível
            botao.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adiciona uma borda preta ao redor
            
            int indice = i;

            botao.addActionListener(e -> tratarClique(indice, botao));
            botoes[i] = botao;
            add(botao);
        }
    }

    // Lógica ao clicar em uma carta
    private void tratarClique(int indice, JButton botao) {
        Carta carta = cartas.get(indice);
        if (carta == null || carta.revelada || botao.getText().length() > 0) return;

        botao.setText(carta.valor);

        if (primeiraCarta == null) {
            primeiraCarta = carta;
            primeiroBotao = botao;
        } else {
            if (primeiraCarta.valor.equals(carta.valor)) {
                carta.revelada = true;
                primeiraCarta.revelada = true;
                primeiraCarta = null;
                primeiroBotao = null;
                paresEncontrados++;

                if (paresEncontrados == cartas.tamanho() / 2) {
                    JOptionPane.showMessageDialog(this, "Parabéns! Você encontrou todos os pares!");
                }
            } else {
                Timer timer = new Timer(1000, e -> {
                    botao.setText("");
                    primeiroBotao.setText("");
                    primeiraCarta = null;
                    primeiroBotao = null;
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    // Início do programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoMemoria());
    }
}