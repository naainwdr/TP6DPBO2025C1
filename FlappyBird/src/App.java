import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Flappy Bird");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(360, 640);
        mainFrame.setLocationRelativeTo(null);

        // Layout untuk mengelola tampilan panel
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Panel game
        FlappyBird gamePanel = new FlappyBird();

        // Load image untuk start button
        ImageIcon originalIcon = new ImageIcon(App.class.getResource("assets/startButton.png"));
        Image image = originalIcon.getImage();

        // Menyesuaikan ukuran gambar
        Image scaledImage = image.getScaledInstance(120, 50, Image.SCALE_SMOOTH);
        ImageIcon startIcon = new ImageIcon(scaledImage);

        // Membuat tombol dengan gambar yang sudah disesuaikan ukurannya
        JButton startButton = new JButton(startIcon);

        // Buat tombol transparan untuk munculkan gambar startnya
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);

        // Atur posisi tombol
        startButton.setBounds(114, 450, startIcon.getIconWidth(), startIcon.getIconHeight());

        // Membuat aksi untuk tombol
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Menyembunyikan tombol
                startButton.setVisible(false);

                // Memulai game
                gamePanel.startGame();
                gamePanel.requestFocus();
            }
        });

        // Menambahkan tombol ke panel game
        gamePanel.setLayout(null);
        gamePanel.add(startButton);

        // Menambahkan panel game ke mainPanel
        mainPanel.add(gamePanel, "Game");

        // Menambahkan mainPanel ke frame utama
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
}
