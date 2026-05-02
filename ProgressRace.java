import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class ProgressRace {

    private static void simulateWork(JProgressBar bar) {

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
        // =========================================================
        // PRZEŁĄCZNIK ARCHITEKTURY
        // false = Klasyczne podejście (Thread + Runnable)
        // true = Wysokopoziomowa pula (ExecutorService)
        // =========================================================
        boolean USE_THREAD_POOL = false;

        System.out.println("Wyścig rozpoczęty!");
        long startTime = System.currentTimeMillis();
        // TWORZENIE I URUCHAMIANIE KLASYCZNYCH WĄTKÓW
        if (!USE_THREAD_POOL) {
            Thread[] threads = new Thread[10];

            for (int i = 0; i < 10; i++) {
                final int index = i;
                threads[i] = new Thread(() -> simulateWork(bars[index]));
                threads[i].start();
            }
            // OCZEKIWANIE NA ZAKOŃCZENIE WSZYSTKICH WĄTKÓW
            for (int i = 0; i < 10; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                }
            }
        } else {
            // TODO: Tutaj trzeba zaimplementować ExecutorService
            // 1. Stworzyć pulę np. Executors.newFixedThreadPool(3)
            // 2. Przekazać zadania simulateWork przez executor.submit()
            // 3. Zamknąć executor i poczekać na zakończenie (awaitTermination)

            // 1. Stworzyć pulę np. Executors.newFixedThreadPool(3)
    java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(3);

    for (int i = 0; i < 10; i++) {
        final int index = i;
        // 2. Przekazać zadania simulateWork przez executor.submit()
        executor.submit(() -> simulateWork(bars[index]));
    }

    // 3.Zamknąc executor i poczekać na zakończenie (awaitTermination)
    executor.shutdown();
    try {
        // Czekaj max 10 min na zakonczenie wszystkich zadan
        executor.awaitTermination(10, java.util.concurrent.TimeUnit.MINUTES);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Wyścig zakończony! Czas trwania: " + (endTime - startTime) + " ms");

    }
}
