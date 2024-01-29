import java.util.*;
public class BG {
    static class Flag {
        int Alpha, Beta;
        Flag(int Alpha, int Beta) {
            this.Alpha = Alpha;
            this.Beta = Beta;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int meta = scanner.nextInt();
        int neo = scanner.nextInt();
        int[][] flex = new int[meta][neo];
        for (int i = 0; i < meta; i++) {
            for (int j = 0; j < neo; j++) {
                flex[i][j] = scanner.nextInt();
            }
        }
        int SourceOne = scanner.nextInt();
        int SourceTwo = scanner.nextInt();
        Flag srcOb = new Flag(SourceOne, SourceTwo);
        int finalAlpha = scanner.nextInt();
        int finalBeta = scanner.nextInt();
        Flag dstOb = new Flag(finalAlpha, finalBeta);
        int useAlpha = scanner.nextInt();
        int useBeta = scanner.nextInt();
        Flag mvOb = new Flag(useAlpha, useBeta);
        int answer = leastTurn(flex, srcOb, dstOb, mvOb);
        System.out.println(answer);
    }
    static int leastTurn(int[][] flex, Flag srcOb, Flag dstOb, Flag mvOb) {
        Queue<Flag> queLL = new LinkedList<>();
        queLL.add(srcOb);
        int[][] far = new int[flex.length][flex[0].length];
        for (int i = 0; i < flex.length; i++) {
            for (int j = 0; j < flex[0].length; j++) {
                far[i][j] = Integer.MAX_VALUE;
            }
        }
        far[srcOb.Alpha][srcOb.Beta] = 0;
        while (!queLL.isEmpty()) {
            Flag currOb = queLL.poll();
            for (int i = 0; i < 4; i++) {
                int currentAlpha = currOb.Alpha + mvOb.Alpha;
                int currentBeta = currOb.Beta + mvOb.Beta;

                if (useMove(currentAlpha, currentBeta, flex.length, flex[0].length) && flex[currentAlpha][currentBeta] == 0) {
                    if (far[currentAlpha][currentBeta] > far[currOb.Alpha][currOb.Beta] + 1) {
                        far[currentAlpha][currentBeta] = far[currOb.Alpha][currOb.Beta] + 1;
                        queLL.add(new Flag(currentAlpha, currentBeta));
                    }
                }
                int tmpA = mvOb.Alpha;
                mvOb.Alpha = mvOb.Beta;
                mvOb.Beta = -tmpA;
            }
        }
        return far[dstOb.Alpha][dstOb.Beta] == Integer.MAX_VALUE ? -1 : far[dstOb.Alpha][dstOb.Beta];
    }
    static boolean useMove(int Alpha, int Beta, int meta, int neo) {
        return Alpha >= 0 && Alpha < meta && Beta >= 0 && Beta < neo;
    }
}
