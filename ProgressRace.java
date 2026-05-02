import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class ProgressRace {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        JFrame frame = new JFrame("Postęp wyścigu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(10, 1, 5, 5));
        frame.setSize(400, 500);

        JProgressBar[] bars = new JProgressBar[10];

        for (int i = 0; i < 10; i++) {
            bars[i] = new JProgressBar(0, 100);
            bars[i].setStringPainted(true);
            frame.add(bars[i]);
        }

        frame.setVisible(true);

        System.out.println("Wyścig rozpoczęty!");


    }
}
