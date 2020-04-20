public class Cell {
    private int valueOfCell = 0;
    private boolean containsOperator = false;
    private int targetValue;
    private String operator;
    private int cageNumber;
    private int x,y;
    
    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    void setCellValue(int value){
        this.valueOfCell = value;
    }
    
    int getCellValue(){
        return valueOfCell;
    }
    
    boolean ContainsOperatorTarget(){
        return containsOperator;
    }
    
    void setOperatorTarget(int value, String operator){
        this.targetValue = value;
        this.operator = operator;
        this.containsOperator = true;
    }
    
    int getTargetValue(){
        return targetValue;
    }
    
    String getOperator(){
        return operator;
    }
    
    void setCageNumber(int cageNumber){
        this.cageNumber = cageNumber;
    }
    
    int getCageNumber(){
        return cageNumber;
    }
    
    int getColumn(){
        return x;
    }    
    int getRow(){
        return y;
    }
}
