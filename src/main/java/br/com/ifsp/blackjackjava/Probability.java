package br.com.ifsp.blackjackjava;

import java.util.Random;

public class Probability {

    private static Random random = new Random();

    /**
     * Retorna verdadeiro com base na taxa de probabilidade especificada.
     *
     * @param probability A taxa de probabilidade desejada (entre 0.0 e 1.0).
     * @return true se o número aleatório gerado for menor que a probabilidade, false caso contrário.
     */
    public static boolean getBoolean(double probability) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("A probabilidade deve estar entre 0.0 e 1.0");
        }
        return random.nextDouble() < probability;
    }

    public static void main(String[] args) {
        double probability = 0.5; // 70% de chance de retornar verdadeiro
        int trueCount = 0;
        int falseCount = 0;
        int iterations = 10000;

        for (int i = 0; i < iterations; i++) {
            if (getBoolean(probability)) {
                trueCount++;
            } else {
                falseCount++;
            }
        }

        System.out.println("Verdadeiro: " + trueCount);
        System.out.println("Falso: " + falseCount);
    }
    
}