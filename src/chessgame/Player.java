/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

/**
 *
 * @author Nick
 */
public class Player {
    
    String color;
    boolean isTurn;
    boolean isChecked;
    
    //make it easy to keep track of king's position on board, if we do not have
    //each player have own array of pieces
    int positionOfKing;
    
    public Player(String color){
        this.color = color;
    }
    
    public void setIsTurn(boolean turn){
        this.isTurn = turn;
    }
    
    public boolean getIsTurn(){
        return this.isTurn;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public boolean isChecked(){
        
        //pseduocode
        //this is not as efficient as having each player have their own separate
        //arrayLists of pieces but that may be a hassle to keep track of/update.
        
        for(int i = 0; i < ChessBoard.pieces.length; i++){
            //these can be combined into 1 if but it's long so i broke into 2
            if (ChessBoard.getIndex(i) != null && ChessBoard.getIndex(i).getColor().equals(this.getColor()) == false){        
                if(ChessBoard.getIndex(i).isValidMove(this.positionOfKing)){
                    this.isChecked = true;
                    break;
                }
            }
        }
        
        
        
        return this.isChecked;
    }
    
    public void setIsChecked(boolean check){
        this.isChecked = check;
    }
    
}
