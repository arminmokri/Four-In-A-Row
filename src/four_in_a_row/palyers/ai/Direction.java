
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
public class Direction {

    int Evalution;
    //
    int Vertical; // |
    int Horizontal; // -
    int Slash; // /
    int Backslash; // \
    //
    int Right;
    int Left;
    int Down;
    int Left_Up;
    int Left_Down;
    int Right_Up;
    int Right_Down;

    public Direction() {
        Right = 0;
        Left = 0;
        Down = 0;
        Left_Up = 0;
        Left_Down = 0;
        Right_Up = 0;
        Right_Down = 0;
    }

    public void Calculate() {
        Vertical = Down + 1;
        Horizontal = Left + 1 + Right;
        Slash = Right_Up + 1 + Left_Down;
        Backslash = Left_Up + 1 + Right_Down;
        Evalution
                = (int) (Math.pow((double) Vertical, 2.0)
                + Math.pow((double) Horizontal, 2.0)
                + Math.pow((double) Slash, 2.0)
                + Math.pow((double) Backslash, 2.0));
    }

    /*
    public void Print() {
        System.out.print("Right=" + Right + " Left=" + Left + " Down=" + Down);
        System.out.println(" Left_Up=" + Left_Up + " Left_Down=" + Left_Down + " Right_Up=" + Right_Up + " Right_Down=" + Right_Down);
    }*/
}
