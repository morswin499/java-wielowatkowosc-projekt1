import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class ProgressRace {

    private static void simulateWork(JProgressBar bar) {
        // Tworzymy nowy wątek dla każdego paska postępu (klasyczne podejście)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;

                while (progress < 100) {
                    // Losowy przyrost postępu (od 1 do 5), aby każdy "plik" pobierał się w innym
                    // tempie
                    progress += (int) (Math.random() * 5) + 1;
                    if (progress > 100)
                        progress = 100;

                    final int currentProgress = progress;

                    // Dobra praktyka: aktualizacje interfejsu graficznego (Swing)
                    // powinny być zlecane do głównego wątku okienkowego (Event Dispatch Thread)
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            bar.setValue(currentProgress);
                        }
                    });

                    // Usypiamy wątek na losowy czas (np. od 50 do 150 ms)
                    try {
                        Thread.sleep((int) (Math.random() * 100) + 50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        });

        // Uruchamiamy utworzony wątek
        thread.start();
    }

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

        // Uruchamiamy symulację dla każdego z 10 pasków
        for (int i = 0; i < 10; i++) {
            simulateWork(bars[i]);
        }


    }
}
