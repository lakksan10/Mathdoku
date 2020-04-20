import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Lakksan
 */
public class Solver {
    int N = 4;
    int NCage = 9;
    int board[][] = new int[N][N];
    int cageBoardMatrix[][] = {
                               {0,1,2,2},
                               {0,3,3,4},
                               {5,6,6,4},
                               {7,7,8,8}
                              };
    Cage cages[] = new Cage[NCage];
    Cell grid[][] = new Cell[N][N];
    
    void initialize(){
        Scanner myscan = new Scanner(System.in);
        int x;
        for (int i = 0; i < NCage; i++) {
            cages[i] = new Cage(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                x = cageBoardMatrix[i][j];
                board[i][j] = 0;
                grid[i][j] = new Cell(i,j);
                cages[x].addMember(grid[i][j]);
            }
        }
        setCageRules();
    }
    void setCageRules(){
        cages[0].setOperatorTarget(7, "+");
        cages[1].setOperatorTarget(2,  "=");
        cages[2].setOperatorTarget(2, "-");
        cages[3].setOperatorTarget(3,  "-");
        cages[4].setOperatorTarget(2,  "/");
        cages[5].setOperatorTarget(1,  "=");
        cages[6].setOperatorTarget(6,"x");
        cages[7].setOperatorTarget(3,  "+");
        cages[8].setOperatorTarget(7, "+");
//        cages[9].setOperatorTarget(7,  "+");
//        cages[10].setOperatorTarget(30, "x");
//        cages[11].setOperatorTarget(6,  "x");
//        cages[12].setOperatorTarget(9,  "+");
//        cages[13].setOperatorTarget(8,  "+");
//        cages[14].setOperatorTarget(2,  "/");  
    }
    void printResult(){
        for (int i = 0; i < N; i++) {
            System.out.println("/n");
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j]);
            }
        }
    }
    boolean solve(){
        Cell emptyCell = findEmpty();
        if(emptyCell != null){
            for (int i = 0; i < N; i++) {
                if(isValid(emptyCell.getRow(), emptyCell.getColumn(),i)){
                    board[emptyCell.getRow()][emptyCell.getColumn()] = i;
                    if(solve()){
                        return true;
                    }
                    board[emptyCell.getRow()][emptyCell.getColumn()]= 0;
                }
            }
            return false;
        }
        else{
                    return true;
        }
    }
    
    Cell findEmpty(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 0){
                     return grid[i][j];
                }
            }
        }
        return null;
    }
    
    boolean isValid(int row, int column, int cellValue){
        return (checkRow(row,cellValue) && checkCol(column,cellValue) && checkCage(cageBoardMatrix[row][column], cellValue));      
    }
    
    boolean checkCol(int column, int cellValue){
        for (int row = 0; row < N; row++) {
            if(board[row][column] == cellValue){
                return false;
            }
        }
        return true;
    }
    
    boolean checkRow(int row, int cellValue){
        for (int column = 0; column < N; column++) {
            if(board[row][column] == cellValue){
                return false;
            }
        }
        return true;
    }
    
    boolean checkCage(int x, int num){
        int total = 0;
        if("+".equals(cages[x].getOperator())){
            total = num;
            LinkedList<Cell> theseMembers = cages[x].getMembers();
            for (int i = 0; i < theseMembers.size(); i++) {
                Cell thisCell = theseMembers.getFirst();
                total += board[thisCell.getRow()][thisCell.getColumn()];
            }            
            if(total <= cages[x].getTargetValue()){
                return true;
            }
        }
        else if("-".equals(cages[x].getOperator())){
            Cell cellA = cages[x].getMembers().getFirst();
            Cell cellB = cages[x].getMembers().get(1);
            int num1 = board[cellA.getRow()][cellA.getColumn()];
            int num2 = board[cellB.getRow()][cellB.getColumn()];
            if(num1 == 0){
                if(num2 == 0){
                    return true;
                }
                else if(num2-num == cages[x].getTargetValue()){
                    return true;
                }
                else if(num-num2 == cages[x].getTargetValue()){
                    return true;
                }
            }    
            else if(num1 - num == cages[x].getTargetValue()){
                return true;
            }
            else if(num-num1 == cages[x].getTargetValue()){
                return true;
            }
        }
        else if("x".equals(cages[x].getOperator())){
            total = num;
            LinkedList<Cell> theseMembers = cages[x].getMembers();
            for (int i = 0; i < theseMembers.size(); i++) {
                Cell thisCell = theseMembers.getFirst();
                total *= board[thisCell.getRow()][thisCell.getColumn()];
            }
            if(total <= cages[x].getTargetValue()){
                return true;
            }
        }
        else if("/".equals(cages[x].getOperator())){
            Cell cellA = cages[x].getMembers().getFirst();
            Cell cellB = cages[x].getMembers().get(1);
            int num1 = board[cellA.getRow()][cellA.getColumn()];
            int num2 = board[cellB.getRow()][cellB.getColumn()];   
            if(num1 == 0){
                if(num2 == 0){
                    return true;
                }
                else if(num2/num == cages[x].getTargetValue()){
                    return true;
                }
                else if(num/num2 == cages[x].getTargetValue()){
                    return true;
                }
            }    
            else if(num1/num == cages[x].getTargetValue()){
                return true;
            }
            else if(num/num1 == cages[x].getTargetValue()){
                return true;
            }            
        }
        return false;
    }
}
