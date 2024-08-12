import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFourGUI extends JFrame implements ActionListener {
    private final int rows = 6;
    private final int cols = 7;
    private final JButton[][] buttons = new JButton[rows][cols];
    private ConnectFour game = new ConnectFour();
    private String player1Name;
    private String player2Name;
    private int currentPlayer = 1;

    public ConnectFourGUI() {
        player1Name = getPlayerName("Enter name for Player 1:");
        player2Name = getPlayerName("Enter name for Player 2:");

        if (player1Name == null || player1Name.trim().isEmpty()) {
            player1Name = "Player 1";
        }
        if (player2Name == null || player2Name.trim().isEmpty()) {
            player2Name = "Player 2";
        }

        setTitle("Connect Four - Gaming Theme");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(rows, cols));
        setResizable(false);

        getContentPane().setBackground(Color.BLACK);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Orbitron", Font.BOLD, 24));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setForeground(Color.WHITE);
                buttons[row][col].setBackground(Color.DARK_GRAY);
                buttons[row][col].setOpaque(true);
                buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    private String getPlayerName(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(221, 160, 221));

        JLabel label = new JLabel(message);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Orbitron", Font.BOLD, 16)); // Set font

        JTextField textField = new JTextField(10);
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.GREEN);
        textField.setCaretColor(Color.GREEN); // Set cursor color to green
        textField.setFont(new Font("Orbitron", Font.BOLD, 16));

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        okButton.setBackground(Color.DARK_GRAY);
        okButton.setForeground(Color.GREEN);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        cancelButton.setBackground(Color.DARK_GRAY);
        cancelButton.setForeground(Color.GREEN);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(221, 160, 221));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        JDialog dialog = new JDialog(this, "Enter Name", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        okButton.addActionListener(e -> dialog.setVisible(false));
        cancelButton.addActionListener(e -> {
            textField.setText("");
            dialog.setVisible(false);
        });

        dialog.setVisible(true);
        return textField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (buttonClicked == buttons[row][col]) {
                    if (game.dropPiece(col, currentPlayer)) {
                        buttons[row][col].setText(currentPlayer == 1 ? player1Name : player2Name);
                        buttons[row][col].setForeground(Color.ORANGE); // Set the player's name color to orange
                        buttons[row][col].setEnabled(false);
                        int winner = game.checkWinner();
                        if (winner != 0) {
                            JOptionPane.showMessageDialog(this, (winner == 1 ? player1Name : player2Name) + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            resetBoard();
                        } else if (winner == -1) {
                            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            resetBoard();
                        } else {
                            currentPlayer = 3 - currentPlayer;
                        }
                    }
                    break;
                }
            }
        }
    }

    private void resetBoard() {
        game = new ConnectFour();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setForeground(Color.WHITE);
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConnectFourGUI::new);
    }
}
