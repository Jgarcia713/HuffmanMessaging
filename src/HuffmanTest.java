import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class HuffmanTest {
    
    @Test
    public void testEncodeDecode() {
        String testMessage = "This message is a bit more convoluted";
        Huffman huffman = new Huffman(testMessage);

        String encodedMessage = huffman.encode(testMessage);
        System.out.println("Encoded: " + (encodedMessage));

        String decodedMessage = huffman.decode(encodedMessage);
        System.out.println("Decoded: " + decodedMessage);

        assertEquals(testMessage, decodedMessage, "Decoded message does not match the original message");
    }
    
    @Test
    public void testSingleCharacter() {
        Huffman huffman = new Huffman("a");
        String encoded = huffman.getEncoding();
        String decoded = huffman.decode(encoded);
        assertEquals("a", decoded);
    }
    
    @Test
    public void testRepeatedCharacters() {
        Huffman huffman = new Huffman("aaaaaaa");
        String encoded = huffman.getEncoding();
        String decoded = huffman.decode(encoded);
        assertEquals("aaaaaaa", decoded);
    }
    
    @Test
    public void testUniqueCharacters() {
        Huffman huffman = new Huffman("abcdef");
        String encoded = huffman.getEncoding();
        String decoded = huffman.decode(encoded);
        assertEquals("abcdef", decoded);
    }
    
    @Test
    public void testNumericalData() {
        Huffman huffman = new Huffman("1234567890");
        String encoded = huffman.getEncoding();
        String decoded = huffman.decode(encoded);
        assertEquals("1234567890", decoded);
    }
    
    @Test
    public void testSpecialCharacters() {
        Huffman huffman = new Huffman("!@#$%^&*()_+");
        String encoded = huffman.getEncoding();
        String decoded = huffman.decode(encoded);
        assertEquals("!@#$%^&*()_+", decoded);
    }
    
    @Test
    public void testCaseSensitivity() {
        Huffman huffman = new Huffman("aAaAaAaA");
        String encoded = huffman.getEncoding();
        String decoded = huffman.decode(encoded);
        assertEquals("aAaAaAaA", decoded);
    }
}
