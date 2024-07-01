import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RandomGeneratorTest {

    private RandomGenerator game;

    @BeforeEach
    public void setUp() {
        game = new RandomGenerator();
    }

    @Test
    public void testGenerateRandomNumber() {
        int number = game.generateRandomNumber();
        assertTrue(number >= 1 && number <= 100, "Random number should be between 1 and 100");
    }

    @Test
    public void testCheckGuessCorrect() {
        game.correctNumber = 50;
        assertTrue(game.checkGuess(50), "Guess should be correct");
    }

    @Test
    public void testCheckGuessTooHigh() {
        game.correctNumber = 50;
        assertFalse(game.checkGuess(70), "Guess should be too high");
    }

    @Test
    public void testCheckGuessTooLow() {
        game.correctNumber = 50;
        assertFalse(game.checkGuess(30), "Guess should be too low");
    }

    @Test
    public void testCheckGuessIncorrect() {
        game.correctNumber = 50;
        assertFalse(game.checkGuess(55), "Guess should be incorrect");
    }

//    @Test
//    public void testGetInput() {
//        String input = "5";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        String userInput = RandomGenerator.getInput("How many rounds do you want to play: ");
//        assertEquals("5", userInput, "The input should be 5");
//    }
}
