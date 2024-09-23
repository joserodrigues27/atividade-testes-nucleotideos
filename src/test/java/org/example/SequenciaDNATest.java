/**
 * Classe SequenciaDNATest, destinada para a realização de testes da classe SequenciaDNA
 * @author caiocesar
 * @author joserodrigues
 */

package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SequenciaDNATest {
    private static final String PATHFILE1 = "teste01.txt";
    private static final String PATHFILE2 = "teste02.txt";
    private static final String PATHFILE3 = "teste03.txt";
    private static final String PATHFILE4 = "teste04.txt";
    private static final String PATHFILE5 = "teste05.txt";
    private static final String PATHFILE6 = "teste06.txt";

    @BeforeAll
    static void beforeAll() {
        try {
            Files.write(Path.of(PATHFILE1), "AAAGTCTGAC".getBytes());
            Files.write(Path.of(PATHFILE2), "AACTGTCGBA".getBytes());
            Files.write(Path.of(PATHFILE3), "ABC TEM FALHA".getBytes());
            Files.write(Path.of(PATHFILE5), "".getBytes());
            Files.write(Path.of(PATHFILE6), "ACGTEATTGC".getBytes());
        } catch (IOException ioe) {
            System.out.println("Erro ao escrever o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está certo para uma sequência válida")
    public void calculaNucleotideosTeste01() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(PATHFILE1);
            assertArrayEquals(new int[] {4, 2, 2, 2, 0}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está certo para outra sequência válida")
    public void calculaNucleotideosTeste02() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(PATHFILE2);
            assertArrayEquals(new int[]{3, 2, 2, 2, 1}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado é null, quando o número de caracteres inválidos for superior a 10% do tamanho da sequência")
    public void calculaNucleotideosTeste03() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(PATHFILE3);
            assertNull(result);
        } catch (IOException ioe) {
        System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se lança exceção do tipo IOException")
    public void calculaNucleotideosTeste04() {
        assertThrowsExactly(FileNotFoundException.class, () -> SequenciaDNA.calculaNucleotideos(PATHFILE4));
    }

    @Test
    @DisplayName("Verifica se o valor retornado está certo para um arquivo vazio")
    public void calculaNucleotideosTeste05() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(PATHFILE5);
            assertArrayEquals(new int[]{0, 0, 0, 0, 0}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está certo, quando o número de caracteres inválidos for igual a 10% do tamanho da sequência")
    public void calculaNucleotideosTeste06() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(PATHFILE6);
            assertArrayEquals(new int[]{2, 2, 2, 3, 1}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao ler o arquivo: " + ioe.getMessage());
        }
    }

    @AfterAll
    static void afterAll() throws IOException {
        try {
            Files.deleteIfExists(Path.of(PATHFILE1));
            Files.deleteIfExists(Path.of(PATHFILE2));
            Files.deleteIfExists(Path.of(PATHFILE3));
            Files.deleteIfExists(Path.of(PATHFILE5));
            Files.deleteIfExists(Path.of(PATHFILE6));
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar deletar o arquivo: " + ioe.getMessage());
        }

    }
}
