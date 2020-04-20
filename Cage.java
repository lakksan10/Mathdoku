import java.util.LinkedList;
import javafx.scene.paint.Color;

/**
 *
 * @author Lakksan
 */
public class Cage {
    LinkedList<Cell> members = new LinkedList<>();
    int cageNumber;
    Color cageColor;
    int targetValue;
    String operator;
  
    Cage(int cageNumber){
        this.cageNumber = cageNumber;
    }
    
    void addMember(Cell newMember){
        this.members.add(newMember);
    }
    
    void setColor(Color cageColor){
        this.cageColor = cageColor;
    }
    
    void setOperatorTarget(int value, String operator){
        this.targetValue = value;
        this.operator = operator;
    }
    
    String getOperator(){
        return operator;
    }
    
    int getTargetValue(){
        return targetValue;
    }
    Color getCageColor(){
        return cageColor;
    }
    
    LinkedList<Cell> getMembers(){
        return members;
    }
}
