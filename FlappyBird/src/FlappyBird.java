import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    //pipes attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // game logic
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean gameOver = false;
    int score = 0;
    JLabel scoreLabel;
    JLabel gameOverLabel;
    JLabel restartLabel;
    boolean gameStart = false;

    // constructor
    public FlappyBird(){
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);
        this.setLayout(null);
        scoreLabel.setBounds(10, 10, 200, 30); // Posisi x, y, lebar, tinggi
        this.add(scoreLabel);

        // GAME OVER Label
        gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBackground(new Color(255, 255, 255, 100));
        gameOverLabel.setOpaque(true); // Agar latar belakang terlihat
        gameOverLabel.setBounds(-10, frameHeight / 2 - 30, frameWidth, 30);
        gameOverLabel.setVisible(false);
        this.add(gameOverLabel);

        // Restart Label
        restartLabel = new JLabel("Press R to Restart", SwingConstants.CENTER);
        restartLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
        restartLabel.setForeground(Color.RED);
        restartLabel.setBackground(new Color(255, 255, 255, 100));
        restartLabel.setOpaque(true); // Agar latar belakang terlihat
        restartLabel.setBounds(-8, frameHeight / 2, frameWidth, 30);
        restartLabel.setVisible(false);
        this.add(restartLabel);


        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move() {
        if(!gameOver && gameStart) {
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0));

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

                // Tambah skor jika player berhasil melewati pipa atas (pipa genap indeksnya)
                if (i % 2 == 0 && !pipe.getScored()) {
                    if (pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                        score++;
                        pipe.setScored(true);
                        scoreLabel.setText("Score: " + score);
                    }
                }

                // Buat Rectangle untuk player dan pipe
                Rectangle playerRect = new Rectangle(
                        player.getPosX(), player.getPosY(),
                        player.getWidth(), player.getHeight()
                );

                Rectangle pipeRect = new Rectangle(
                        pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight()
                );

                // Cek tabrakan
                if (playerRect.intersects(pipeRect)) {
                    endGame();  // method untuk menghentikan permainan
                    return;
                }
            }

            if (player.getPosY() + player.getHeight() >= frameHeight) {
                endGame();
            }
        }
    }

    public void startGame() {
        if (!gameStart) {
            gameStart = true;
            gameOver = false;
            score = 0;
            scoreLabel.setText("Score: 0");
            pipes.clear();
            player.setPosY(playerStartPosY);
            player.setVelocityY(0);
            gameLoop.start();
            pipesCooldown.start();
            requestFocus(); // biar bisa deteksi tombol SPACE
        }
    }
    public void endGame() {
        gameOver = true;
        gameLoop.stop();
        pipesCooldown.stop();

        // Tampilkan label game over dan restart
        gameOverLabel.setVisible(true);
        restartLabel.setVisible(true);

        System.out.println("Game Over!");
    }

    public void resetGame() {
        // Reset player
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        // Kosongkan pipa
        pipes.clear();

        // Reset flag
        gameOver = false;

        // Sembunyikan label game over dan restart
        gameOverLabel.setVisible(false);
        restartLabel.setVisible(false);

        // Reset score
        score = 0;
        scoreLabel.setText("Score: 0");

        // Start ulang timer
        pipesCooldown.start();
        gameLoop.start();
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (gameStart) {
            move();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        if(gameStart && !gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }

        if(gameOver && e.getKeyCode() == KeyEvent.VK_R){
            resetGame();
            score = 0;
            scoreLabel.setText("Score: 0");
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }
}
