package main;

import java.util.Scanner;

public class SquareCircle {

    public static int[][] grid;
    public static int w;
    public static int h;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        w = in.nextInt();
        h = in.nextInt();
        int circleX = in.nextInt();
        int circleY = in.nextInt();
        int r = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x3 = in.nextInt();
        int y3 = in.nextInt();
        grid = new int[h][w];
        drawSquare(x1, y1, x3, y3);
        drawCricle(circleX,circleY,r);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("#");
            }
            System.out.println();
        }
    }

    private static void drawSquare(int x1, int y1, int x3, int y3) {
        int x2 = Math.round((x1 + x3 + y3 - y1) / 2);
        int y2 = Math.round((y1 + y3 + x1 - x3) / 2);
        int x4 = Math.round((x1 + x3 + y1 - y3) / 2);
        int y4 = Math.round((y1 + y3 + x3 - x1) / 2);

        drawLine(x1, y1, x2, y2);
        drawLine(x1, y1, x4, y4);
        drawLine(x3, y3, x4, y4);
        drawLine(x3, y3, x2, y2);
        
        int[] fillPoint = findFillPoint(x1, y1, x3, y3);
        if (fillPoint != null)
            fillSquare(fillPoint[0], fillPoint[1]);
         
    }

    private static int[] findFillPoint(int x1, int y1, int x3, int y3) {
        if (inGrid(x1, y1)) {
            return getMidPoint(x1, y1, x3, y3);
        }
        else if (inGrid(x3, y3)) {
            return getMidPoint(x3, y3, x1, y1);
        }
        return null;
    }

    private static int[] getMidPoint(int x1, int y1, int x2, int y2) {
        if (!equal(x1, y1, x2, y2)) {
            int x = Math.round((x1 + x2) / 2), y = Math.round((y1 + y2) / 2);
            if (inGrid(x, y)) {
                return new int[] {
                        x, y };
            }
            else {
                return getMidPoint(x1, y1, x, y);
            }
        }
        return null;
    }

    private static boolean equal(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    private static boolean inGrid(int x, int y) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }

    private static void fillSquare(int x, int y) {
        if (inGrid(x, y)) {
            if (grid[y][x] == 0) {
                grid[y][x] = 1;
                fillSquare(x + 1, y);
                fillSquare(x - 1, y);
                fillSquare(x, y + 1);
                fillSquare(x, y - 1);
            }
        }
    }

    private static void drawLine(int x, int y, int x2, int y2) {
        int w = x2 - x ;
        int h = y2 - y ;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        int longest = Math.abs(w) ;
        int shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;            
        }
        int numerator = longest >> 1 ;
        for (int i=0;i<=longest;i++) {
            if(inGrid(x,y))
                grid[y][x]=1;
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }
    }

    private static void drawCricle(int circleX, int circleY, int r) {
        int topY, bottomY, leftX, rightX;
        topY = circleY - r;
        bottomY = circleY + r;
        leftX = circleX;
        rightX = circleX;

        for (int i = 0; i <= r; i++, topY++, bottomY--) {
            while (distanceBetweenTwoPoints(circleX, circleY, leftX, topY) <= r)
                leftX--;
            while (distanceBetweenTwoPoints(circleX, circleY, rightX, topY) <= r)
                rightX++;

            if (topY >= 0 && topY < h)
                for (int k = (leftX + 1) < 0 ? 0 : (leftX + 1); k < (rightX >= w ? w : rightX); k++) {
                    grid[topY][k] = 1;
                }
            if (bottomY < h && bottomY >= 0)
                for (int k = (leftX + 1) < 0 ? 0 : (leftX + 1); k < (rightX >= w ? w - 1 : rightX); k++) {
                    grid[bottomY][k] = 1;
                }
        }
    }

    private static double distanceBetweenTwoPoints(int firstX, int firstY, int secondX, int secondY) {
        return Math.sqrt(Math.pow(firstX - secondX, 2) + Math.pow(firstY - secondY, 2));
    }

}
