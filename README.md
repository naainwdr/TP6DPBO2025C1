# TP6DPBO2025C1
Saya Nina Wulandari dengan NIM 2312091 mengerjakan Tugas Praktikum 6 dalam mata kuliah DPBO untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Desain Class


# Penjelasan Atribut dan Methods

1. Class Player → Kelas ini merepresentasikan karakter burung yang dikendalikan oleh pemain.
    - int posX, posY → posisi burung di layar, x adalah horizontal dan y adalah vertikal.
    - int width, height → ukuran lebar dan tingginya burung.
    - Image image → gambar burung yang tampil di frame.
    - int velocityY → kecepatan vertikal burung saat bergerak atau melompat.
  
    Methods:
    - getter dan setter untuk akses atribut.

2. Class Pipe → Kelas ini merepresentasikan rintangan (pipa) atas dan bawah dalam permainan.
    - int posX, posY, width, height → posisi dan ukuran pipa.
    - Image image → gambar pipa.
    - int velocityX → kecepatan pergerakan pipa dari kanan ke kiri.
    - boolean passed, menandakan apakah burung sudah melewati pipa.
    - boolean scored → menandakan apakah skornya sudah ditambahkan.
    
    Methods:
    - getter dan setter untuk akses atribut.

3. Class FlappyBird → Kelas utama yang mengatur permainan, logika game dan tampilan.
    - Frame: frameWidth, frameHeight → ukuran jendela game
    - Gambar: backgroundImage, birdImage, lowerPipeImage, upperPipeImage → import image visual
    - Objek: Player player, ArrayList<Pipe> pipes 
    - Timer gameLoop → timer untuk mengupdate game 
    - int pipesCooldown → waktu jeda pipa baru
    - boolean gameOver, boolean gameStart → status game mulai dan berakhir
    - int score
    - int gravity
    - Komponen GUI: JLabel scoreLabel, gameOverLabel, restartLabel
    
    Methods:
    - paintComponent(Graphics g) → menggambar ulang panel.
    - draw() → Menggambar semua objek game
    - move() →	Update posisi player dan pipa, tabrakan, skor
    - startGame() →	Memulai permainan
    - endGame()	→ Mengakhiri permainan
    - resetGame()	→ Reset ulang state permainan
    - actionPerformed(ActionEvent e) → melakukan update game tiap interval timer.
    - keyPressed(KeyEvent e) → input space untuk loncat, R untuk restart.
    - placePipes() → Menambahkan pipa ke layar

4. Class App → Entry point program, pengatur JFrame, dan start button.
    - JFrame frame.
    
    Methods:
    - main(String[] args) → Membuka jendela utama program.
      - Menambahkan FlappyBird ke frame.
      - Menambahkan tombol start berupa gambar. Saat diklik:
        - Tombol hilang.
        - Fokus berpindah ke panel game.
        - Game dimulai dengan startGame().


# Penjelasan Alur Program
- Program dimulai dengan menampilkan jendela JFrame setelah di run melalui App.java.
- Di awal, akan muncul gambar background, bird, dan tombol Start berupa gambar di atas panel game (FlappyBird).
- Saat tombol Start ditekan akan membuat tombol hilang secara otomatis dan game akan mulai.

### Alur Permainan:
1. Memulai Game
   - Pemain menekan tombol Space (spasi) untuk membuat burung melompat.
   - Pipa-pipa bergerak ke arah kiri.
   - Jika burung melewati pipa tanpa menabrak, skor bertambah 1.

2. Game Over
   - Jika burung menabrak pipa atau jatuh ke tanah:
     - Game berhenti.
     - Muncul label "GAME OVER" dan instruksi untuk restart.

3. Restart Game
   - Pemain bisa menekan tombol R untuk memulai ulang permainan.
   - Semua posisi, skor, dan status akan di-reset dan memulai ulang permainan.

# Dokumentasi Program
https://github.com/user-attachments/assets/9189e849-9bd9-4621-8a89-0575a275ff61
**Dokumentasi dipercepat 2x*



