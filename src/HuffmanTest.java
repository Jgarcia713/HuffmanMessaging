import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HuffmanTest {
    
    @Test
    public void testEncodeDecode() {
        // The message to test
        String testMessage = "This message is a bit more convoluted";

        // Create an instance of Huffman with the test message
        Huffman huffman = new Huffman(testMessage);

        // Encode the message
        String encodedMessage = huffman.encode(testMessage);
        System.out.println("Encoded: " + encodedMessage);

        // Decode the encoded message
        String decodedMessage = huffman.decode(encodedMessage);
        System.out.println("Decoded: " + decodedMessage);

        // Check if the decoded message matches the original message
        assertEquals(testMessage, decodedMessage, "Decoded message does not match the original message");
    }
}
