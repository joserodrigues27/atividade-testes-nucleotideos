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
            String filePath = sc.nextLine();
            System.out.println(Arrays.toString(calculaNucleotideos(filePath)));
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    public static int[] calculaNucleotideos(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
        String line = Files.readString(path);
        int[] vect = new int[5];
        for (char character : line.toCharArray()) {
            if (character == 'A') {
                vect[0]++;
            } else if (character == 'C') {
                vect[1]++;
            } else if (character == 'G') {
                vect[2]++;
            } else if (character == 'T') {
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
