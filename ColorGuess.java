import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class ColorClue {
    private String color;
    private String clue1;
    private String clue2;
    private String clue3;

    public ColorClue(String color, String clue1, String clue2, String clue3) {
        this.color = color.toLowerCase();
        this.clue1 = clue1;
        this.clue2 = clue2;
        this.clue3 = clue3;
    }

    public String getColor() { return color; }

    public String getClue(int number) {
        switch (number) {
            case 1: return clue1;
            case 2: return clue2;
            case 3: return clue3;
            default: return "";
        }
    }
}


class Red extends ColorClue {
    public Red() {
        super("red", "It's a fruit.", "It's the color of a stop sign.", "It can be seen in roses.");
    }
}

class Yellow extends ColorClue {
    public Yellow() {
        super("yellow", "It's the color of bananas.", "It's the sun.", "It's bright and cheerful.");
    }
}

class Green extends ColorClue {
    public Green() {
        super("green", "It's in nature.", "It's grass.", "It’s the color of leaves.");
    }
}

class Blue extends ColorClue {
    public Blue() {
        super("blue", "It's in the sky.", "It's a calm color.", "It's the color of the ocean.");
    }
}

class Purple extends ColorClue {
    public Purple() {
        super("purple", "It's often seen in grapes.", "It's a royal color.", "It can be a flower too.");
    }
}

class Pink extends ColorClue {
    public Pink() {
        super("pink", "It's a soft color.", "It's often seen in flowers.", "It can be a sweet candy color.");
    }
}

class Orange extends ColorClue {
    public Orange() {
        super("orange", "It's the color of sunsets.", "It's the color of a traffic cone.", "You can find it in pumpkins.");
    }
}


public class ColorGuess {

    private JFrame frame;
    private JLabel clueLabel;
    private JTextField guessField;
    private JButton guessButton;

    private ArrayList<ColorClue> colors = new ArrayList<>();

    private Random random = new Random();
    private ColorClue currentColor;
    private int clueNumber = 1;

    public ColorGuess() {

        
        colors.add(new Red());
        colors.add(new Yellow());
        colors.add(new Green());
        colors.add(new Blue());
        colors.add(new Purple());
        colors.add(new Pink());
        colors.add(new Orange());

        pickNewColor();

        frame = new JFrame("Color Guessing Game");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clueLabel = new JLabel("Clue: " + currentColor.getClue(clueNumber));
        guessField = new JTextField(15);
        guessButton = new JButton("Guess");

        JPanel panel = new JPanel();
        panel.add(clueLabel);
        panel.add(guessField);
        panel.add(guessButton);

        frame.add(panel);
        frame.setVisible(true);

        guessButton.addActionListener(e -> handleGuess());
    }

    private void pickNewColor() {
        currentColor = colors.get(random.nextInt(colors.size()));
        clueNumber = 1;
    }

    private void handleGuess() {
        String guess = guessField.getText();

        if (guess.equalsIgnoreCase(currentColor.getColor())) {
            JOptionPane.showMessageDialog(frame, "Correct! The color was: " + currentColor.getColor());
            colors.remove(currentColor);
            if (colors.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "🎉 You named all the colors!");
                System.exit(0);
            }
            pickNewColor();
            clueLabel.setText("Clue: " + currentColor.getClue(1));
            guessField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Oh, you got it wrong! Try again! :(");
            clueNumber++;
            if (clueNumber <= 3) {
                clueLabel.setText("Clue: " + currentColor.getClue(clueNumber));
            } else {
                clueLabel.setText("No more clues! Try again!");
            }
        }
    }

    public static void main(String[] args) {
        new ColorGuess();
    }
}
