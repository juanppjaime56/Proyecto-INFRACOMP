package Proyecto1;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class ThreadPrincipal {
    private static int N = 3;
    private static CyclicBarrier barrera;
    private static CyclicBarrier barrera2;
    private static boolean[][] M = {
            {true, true, false},
            {true, true, true},
            {false, false, true}
    };

    public static void main(String[] args) {
        barrera = new CyclicBarrier(N);
        barrera2 = new CyclicBarrier(N);
        ArrayList<Buffer> listaBuffers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cantidadVecinos = calcularCantidadVecinos(i, j);
                Cell celula = new Cell(M[i][j], i, j);
                Buffer buffer = new Buffer(i + 1, celula, cantidadVecinos, barrera, barrera2);
                listaBuffers.add(buffer);
                buffer.start();
            }
        }
    }

    private static int calcularCantidadVecinos(int fila, int columna) {
        if ((fila == 0 && columna == 0) || (fila == 0 && columna == N - 1) ||
            (fila == N - 1 && columna == 0) || (fila == N - 1 && columna == N - 1)) {
            return 3;
        } else if (fila == 0 || columna == 0 || columna == N - 1 || fila == N - 1) {
            return 5;
        } else {
            return 8;
        }
    }
}