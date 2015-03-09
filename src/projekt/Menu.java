package projekt;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import projekt.graphics.Screen;
import projekt.input.Keyboard;

public class Menu extends javax.swing.JFrame {

    public static int width = 300;
    public static int height = width / 16 * 9;
    public int scale = 3;
    public static String title = "Mages Battle";
    
    private JFrame frame;
    
    private enum STATE{GAME,MENU};
    private STATE State = STATE.GAME;
    
    private Keyboard key;
    private boolean running = false;
    
    private Screen screen;
    
    public Menu() {
        initComponents();
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        
        addKeyListener(key);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setFocusCycleRoot(false);
        setMaximumSize(new java.awt.Dimension(900, 504));
        setPreferredSize(new java.awt.Dimension(900, 504));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(300, 168));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 168));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 168));
        jPanel1.setLayout(null);

        jTextField1.setBackground(new java.awt.Color(153, 153, 153));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("login");
        jTextField1.setBorder(null);
        jTextField1.setMaximumSize(new java.awt.Dimension(300, 30));
        jTextField1.setMinimumSize(new java.awt.Dimension(300, 30));
        jTextField1.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel1.add(jTextField1);
        jTextField1.setBounds(310, 230, 300, 30);

        jPasswordField1.setBackground(new java.awt.Color(153, 153, 153));
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.setBorder(null);
        jPasswordField1.setMaximumSize(new java.awt.Dimension(300, 30));
        jPasswordField1.setMinimumSize(new java.awt.Dimension(300, 30));
        jPasswordField1.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(310, 270, 301, 30);

        jButton1.setBackground(new java.awt.Color(255, 204, 153));
        jButton1.setFont(new java.awt.Font("48pixel", 1, 14)); // NOI18N
        jButton1.setForeground(java.awt.Color.darkGray);
        jButton1.setText("Log in");
        jButton1.setToolTipText("");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setMaximumSize(new java.awt.Dimension(50, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(50, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(310, 320, 301, 30);

        jButton3.setBackground(new java.awt.Color(255, 204, 153));
        jButton3.setFont(new java.awt.Font("48pixel", 1, 14)); // NOI18N
        jButton3.setForeground(java.awt.Color.darkGray);
        jButton3.setText("Exit");
        jButton3.setToolTipText("");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setDefaultCapable(false);
        jButton3.setFocusPainted(false);
        jButton3.setMaximumSize(new java.awt.Dimension(50, 23));
        jButton3.setMinimumSize(new java.awt.Dimension(50, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(310, 370, 301, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/TITLE.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 920, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Projekt game = new Projekt();
        game.frame.setResizable(false); //blokada zmiany rozmiaru ekranu
        game.frame.setTitle(Projekt.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //standardowe wyjście z programu
        game.frame.setLocationRelativeTo(null); //umieszczenie okna na ekranie
        game.frame.setVisible(true); //umożliwia wyświetlenie okna
        this.setVisible(false);
        game.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

