/**
 * Classe SequenciaDNA
 * @author caiocesar
 * @author joserodrigues
 */

package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class SequenciaDNA {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Digite o caminho do arquivo: ");
            String path = sc.nextLine();
            System.out.print(Arrays.toString(calculaNucleotideos(path)));
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    public static int[] calculaNucleotideos(String filePath) throws IOException {
        Path path1 = Path.of(filePath);
        if (!Files.exists(path1)) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
        String line = Files.readString(path1);
        int[] vect = new int[5];
        for (char letra : line.toCharArray()) {
            if (letra == 'A') {
                vect[0]++;
            } else if (letra == 'C') {
                vect[1]++;
            } else if (letra == 'G') {
                vect[2]++;
            } else if (letra == 'T') {
                vect[3]++;
            } else {
                vect[4]++;
            }
        }
        if ((double) vect[4] / Arrays.stream(vect).sum() > 0.1) {
            return null;
        }
        return vect;
    }
}
