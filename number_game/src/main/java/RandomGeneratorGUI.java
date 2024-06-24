import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGeneratorGUI extends JFrame {
    private int guessMax = 5;
    private int roundsMax = 10;
    private boolean correctGuess = false;
    private int rounds = 1; // Current round number
    private int winTrack = 0;
    private List<Integer> trueCounts = new ArrayList<>();
    private int correctNumber;
    private Random randomNumber = new Random();

    private JTextField userGuessField;
    private JTextArea outputArea;
    private JButton guessButton;
    private JButton startButton;
    private JTextField roundsField;

    public RandomGeneratorGUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Number of rounds:"));
        roundsField = new JTextField();
        inputPanel.add(roundsField);

        inputPanel.add(new JLabel("Your Guess:"));
        userGuessField = new JTextField();
        inputPanel.add(userGuessField);

        startButton = new JButton("Start Game");
        inputPanel.add(startButton);

        guessButton = new JButton("Guess");
        guessButton.setEnabled(false);
        inputPanel.add(guessButton);

        pane.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        pane.add(scrollPane, BorderLayout.CENTER);

        startButton.addActionListener(new StartButtonListener());
        guessButton.addActionListener(new GuessButtonListener());
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userRounds = roundsField.getText();
            try {
                int requestedRounds = Integer.parseInt(userRounds);
                roundsMax = requestedRounds;
                rounds = 1;
                trueCounts.clear();
                winTrack = 0;
                correctGuess = false;
                outputArea.setText("");
                generateNewNumber();
                guessButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(RandomGeneratorGUI.this, "Please enter a valid number of rounds.");
            }
        }
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (rounds <= roundsMax) {
                String userAttempt = userGuessField.getText();
                try {
                    int userGuess = Integer.parseInt(userAttempt);
                    processGuess(userGuess);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RandomGeneratorGUI.this, "Please enter a valid number.");
                }
            } else {
                outputArea.append("Game over. Total rounds won: " + trueCounts.size() + "\n");
                guessButton.setEnabled(false);
            }
        }
    }

    private void generateNewNumber() {
        correctNumber = 1 + randomNumber.nextInt(100); // Generate a random number between 1 and 100
        System.out.println(correctNumber); // For testing purposes
    }

    private void processGuess(int userGuess) {
        if (userGuess == correctNumber) {
            outputArea.append("Round " + rounds + ": Your guess is correct!\n");
            correctGuess = true;
            if (correctGuess) {
                winTrack++;
                trueCounts.add(winTrack);
            }
            rounds++;
            if (rounds <= roundsMax) {
                generateNewNumber();
            }
        } else if (userGuess > (correctNumber + 10)) {
            outputArea.append("Round " + rounds + ": Your guess is too high from the correct guess.\n");
        } else if (userGuess < (correctNumber - 10)) {
            outputArea.append("Round " + rounds + ": Your guess is too low from the correct guess.\n");
        } else {
            outputArea.append("Round " + rounds + ": Your guess is incorrect.\n");
        }

        if (rounds > roundsMax) {
            outputArea.append("Sorry, you've reached your maximum rounds.\nThe correct guess was " + correctNumber + ".\n");
            outputArea.append("Total rounds won: " + trueCounts.size() + "\n");
            guessButton.setEnabled(false);
        }
        correctGuess = false; // Reset correctGuess for the next round
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RandomGeneratorGUI gui = new RandomGeneratorGUI();
            gui.setVisible(true);
        });
    }
}
