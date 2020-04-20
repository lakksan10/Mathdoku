public class MathDoku3 {

    /**
     * @param args the command line arguments
     */
     static   int N = 6;
    static Cell gameGrid[][] = new Cell[N][N];
    static boolean maskContainsOperator[] = {
                                      true,true,false,true,true,false,
                                      false,true,false,false,true,false,
                                      true,false,true,false,false,false,
                                      false,false,true,true,true,false,
                                      true,false,false,false,false,true,
                                      true,false,false,true,false,false
                                      };
    static int cageN = 15;    
    static int cageIndicatorMask[][] = {
        {0,  1, 1,  2,  3,   3},
        {0,  4, 4,  2,  5,   3},
        {6,  6, 7,  7,  5,   3},
        {6,  6, 8,  9, 10,  10},
        {12,12, 8,  9,  9,  12},
        {13,13,13, 14, 14,  12}
    };

    
    static Cage cages[] = new Cage[cageN];
    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.initialize();
        if(solver.solve()){
            solver.printResult();
        }
        else{
            System.out.println("error");
        }
    }

}
