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
    private static final String FILEPATH1 = "teste01.txt";
    private static final String FILEPATH2 = "teste02.txt";
    private static final String FILEPATH3 = "teste03.txt";
    private static final String FILEPATH4 = "teste04.txt";
    private static final String FILEPATH5 = "teste05.txt";
    private static final String FILEPATH6 = "teste06.txt";
    private static final String FILEPATH7 = "teste07.txt";

    @BeforeAll
    static void beforeAll() {
        try {
            Files.write(Path.of(FILEPATH1), "AAAGTCTGAC".getBytes());
            Files.write(Path.of(FILEPATH2), "AACTGTCGBA".getBytes());
            Files.write(Path.of(FILEPATH3), "ABC TEM FALHA".getBytes());
            Files.write(Path.of(FILEPATH5), "".getBytes());
            Files.write(Path.of(FILEPATH6), "ACGTEATTGC".getBytes());
            Files.write(Path.of(FILEPATH7), "AACATGCTGCATGCTGGTAAAACCACTGGGCACCATTGCACAC".getBytes());
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar escrever o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está correto para uma sequência válida")
    public void calculaNucleotideosTeste01() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(FILEPATH1);
            assertArrayEquals(new int[] {4, 2, 2, 2, 0}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está correto para outra sequência válida")
    public void calculaNucleotideosTeste02() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(FILEPATH2);
            assertArrayEquals(new int[] {3, 2, 2, 2, 1}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado é null, quando o número de caracteres inválidos for superior a 10% do tamanho da sequência")
    public void calculaNucleotideosTeste03() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(FILEPATH3);
            assertNull(result);
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se lança uma exceção do tipo FileNotFoundException, quando o arquivo passado como parâmetro não for encontrado")
    public void calculaNucleotideosTeste04() {
        assertThrowsExactly(FileNotFoundException.class, () -> SequenciaDNA.calculaNucleotideos(FILEPATH4));
    }

    @Test
    @DisplayName("Verifica se o valor retornado está correto para um arquivo vazio")
    public void calculaNucleotideosTeste05() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(FILEPATH5);
            assertArrayEquals(new int[] {0, 0, 0, 0, 0}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está correto, quando o número de caracteres inválidos for igual a 10% do tamanho da sequência")
    public void calculaNucleotideosTeste06() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(FILEPATH6);
            assertArrayEquals(new int[] {2, 2, 2, 3, 1}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    @Test
    @DisplayName("Verifica se o valor retornado está correto em relação a um exemplo com uma sequência válida")
    public void calculaNucleotideosTeste07() {
        try {
            int[] result = SequenciaDNA.calculaNucleotideos(FILEPATH7);
            assertArrayEquals(new int[] {13, 13, 9, 8, 0}, result);
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler o arquivo: " + ioe.getMessage());
        }
    }

    @AfterAll
    static void afterAll() {
        try {
            Files.deleteIfExists(Path.of(FILEPATH1));
            Files.deleteIfExists(Path.of(FILEPATH2));
            Files.deleteIfExists(Path.of(FILEPATH3));
            Files.deleteIfExists(Path.of(FILEPATH5));
            Files.deleteIfExists(Path.of(FILEPATH6));
            Files.deleteIfExists(Path.of(FILEPATH7));
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar deletar o arquivo: " + ioe.getMessage());
        }
    }
}
