/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.players;

import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Armin Mokri
 */
public class PlayerGUI extends Player {

    private int move;
    private JLabel[] jLabelsAction;

    public PlayerGUI(int col) {
        super(col);
    }

    public PlayerGUI(int col, int board_size, int nut_size) {
        super(col);
        this.move = -1;
        this.jLabelsAction = new JLabel[board_size];
        for (int i = 0; i < board_size; i++) {
            JLabel jLabel = new JLabel();
            jLabel.setSize(nut_size, nut_size);
            jLabel.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/red25.png")));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabel.setIcon(null);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (move == -1) {
                        for (int i = 0; i < board_size; i++) {
                            if (jLabel == jLabelsAction[i]) {
                                move = i;
                            }
                        }
                    }
                }

            });
            //
            jLabelsAction[i] = jLabel;
        }
    }

    @Override
    public int getMove(four_in_a_row.Board board) {
        try {
            while (move == -1) {
                Thread.sleep(50);
            }
            int move_temp = move;
            move = -1;
            return move_temp;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public JLabel[] getjLabelsAction() {
        return jLabelsAction;
    }

}
