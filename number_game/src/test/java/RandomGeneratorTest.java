import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomGeneratorTest {

    private InputStream originalSystemIn;
    private final StringBuilder output = new StringBuilder();

    @Before
    public void setUp() {
        originalSystemIn = System.in;
        System.setOut(new java.io.PrintStream(System.out) {
            @Override
            public void println(String x) {
                output.append(x).append("\n");
            }
        });
    }

    @After
    public void tearDown() {
        System.setIn(originalSystemIn);
        output.setLength(0);
    }

    @Test
    public void testPlayGame() {
//        String input = "2\n50\n50\n50\n75\n75\n75\n"; // 2 rounds, 3 guesses each
//        simulateUserInput(input);
//
////        List<Integer> results = RandomGenerator.playGame(2, 3);
//
//        // Verify the results
//        assertEquals(2, results.size());
//        assertTrue(output.toString().contains("Rounds won: 2"));
//    }

//    private void simulateUserInput(String input) {
//        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//        System.setIn(inputStream);
    }
}
