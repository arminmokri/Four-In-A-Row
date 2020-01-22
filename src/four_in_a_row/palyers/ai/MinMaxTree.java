/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.palyers.ai;

/**
 *
 * @author Armin Mokri
 */
public class MinMaxTree {

    private MinMaxNode root;
    private int MAX_DEEP;
    private int player;
    private int player_max;
    private int player_min;
    private int move;
    private boolean debug;

    public MinMaxTree(four_in_a_row.Board board, int player, int max_deep, boolean debug) throws InterruptedException {
        this.root = new MinMaxNode(board, MinMaxNode.ROOT, 0, null);
        this.player = player;
        this.MAX_DEEP = max_deep;
        this.debug = debug;
        if (this.player == 1) {
            player_max = 1;
            player_min = 2;
        } else {
            player_max = 2;
            player_min = 1;
        }
        move = -1;
        /*if (board.getNumberOfMoves() == 7) {
            root.Stuffed();
            root.PrintArray("Stuffed", root.stuffed);
            root.PrintBoard();
            root.Evalution(player_max);
            //int[] a = root.columns;
            //root.PrintArray("Column Player " + player_max, a);
            root.Evalution(player_min);
            //int[] b = root.columns;
            //root.PrintArray("Column Player " + player_min, b);
            Thread.sleep(100000);
            System.exit(0);
        }*/
    }

    public void Start() throws Exception {
        MakeTree(root);
        //System.out.println("move=" + move);
        if (debug) {
            PrintTree(root);
        }
    }

    private int MakeTree(MinMaxNode minMaxNode) throws Exception {
        int win = minMaxNode.getBoard().win();
        if (win != 0 || minMaxNode.getLevel() == MAX_DEEP) {
            minMaxNode.EvalutionFunction(player_max, player_min, win);
            return minMaxNode.getValue();
        } else {
            if (minMaxNode.getKind() == MinMaxNode.ROOT) {
                minMaxNode.MakeChilds(player_max);
                for (int i = 0; i < minMaxNode.getChilds().size(); i++) {
                    MinMaxNode minMaxNode_child = minMaxNode.getChilds().get(i);
                    //if (minMaxNode.getValue() != Integer.MAX_VALUE) {
                    //  minMaxNode_child.beta = minMaxNode.getValue();
                    //}
                    // Calculate Recursive Evaluation
                    int RecursiveEvaluation = MakeTree(minMaxNode_child);
                    // Find Min
                    if (minMaxNode.getValue() > RecursiveEvaluation) {
                        minMaxNode.setValue(RecursiveEvaluation);
                        move = minMaxNode_child.getMove();
                    }
                }

            } else if (minMaxNode.getKind() == MinMaxNode.MAX) {
                minMaxNode.MakeChilds(player_min);
                for (int i = 0; i < minMaxNode.getChilds().size(); i++) {
                    //Alpha Beta Pruning
                    if (minMaxNode.beta < minMaxNode.alpha) {
                        return minMaxNode.getValue();
                    }
                    MinMaxNode minMaxNode_child = minMaxNode.getChilds().get(i);
                    minMaxNode_child.alpha = minMaxNode.getValue();
                    // Calculate Recursive Evaluation
                    int RecursiveEvaluation = MakeTree(minMaxNode_child);
                    // Find Max
                    if (minMaxNode.getValue() < RecursiveEvaluation) {
                        minMaxNode.setValue(RecursiveEvaluation);
                        minMaxNode.alpha = RecursiveEvaluation;
                    }
                }
            } else {
                minMaxNode.MakeChilds(player_max);
                for (int i = 0; i < minMaxNode.getChilds().size(); i++) {
                    //Alpha Beta Pruning
                    if (minMaxNode.beta < minMaxNode.alpha) {
                        return minMaxNode.getValue();
                    }
                    MinMaxNode minMaxNode_child = minMaxNode.getChilds().get(i);
                    minMaxNode_child.beta = minMaxNode.getValue();
                    // Calculate Recursive Evaluation
                    int RecursiveEvaluation = MakeTree(minMaxNode_child);
                    // Find Min
                    if (minMaxNode.getValue() > RecursiveEvaluation) {
                        minMaxNode.setValue(RecursiveEvaluation);
                        minMaxNode.beta = RecursiveEvaluation;
                    }

                }
            }
            return minMaxNode.getValue();
        }
    }

    public void PrintTree(MinMaxNode minMaxNode) {

        if (minMaxNode.getChilds().isEmpty()) {
            return;
        } else {

            if (minMaxNode == root) {
                System.out.println("Level=" + minMaxNode.getLevel() + " Move=" + minMaxNode.getMove());
                System.out.println(minMaxNode.getValue());
            }

            System.out.println("Level=" + (minMaxNode.getLevel() + 1) + " Move=" + minMaxNode.getMove());
            for (int i = 0; i < minMaxNode.getChilds().size(); i++) {
                System.out.print(minMaxNode.getChilds().get(i).getMove() + " " + minMaxNode.getChilds().get(i).getValue() + " / ");
            }
            System.out.println();
            for (int i = 0; i < minMaxNode.getChilds().size(); i++) {
                PrintTree(minMaxNode.getChilds().get(i));
            }
        }

    }

    public int getMove() {
        return move;
    }
}
