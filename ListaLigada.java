// Lista ligada das cartas
class ListaLigada {
    No cabeca;

    void adicionar(Carta carta) {
        No novo = new No(carta);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    Carta get(int index) {
        No atual = cabeca;
        int contador = 0;
        while (atual != null) {
            if (contador == index) return atual.carta;
            atual = atual.proximo;
            contador++;
        }
        return null;
    }

    void trocar(int i, int j) {
        Carta ci = get(i);
        Carta cj = get(j);
        if (ci != null && cj != null) {
            String temp = ci.valor;
            ci.valor = cj.valor;
            cj.valor = temp;
        }
    }

    int tamanho() {
        int cont = 0;
        No atual = cabeca;
        while (atual != null) {
            cont++;
            atual = atual.proximo;
        }
        return cont;
    }
}