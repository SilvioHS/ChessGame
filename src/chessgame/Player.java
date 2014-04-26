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
    boolean isCheckmated;
    
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
        
        this.isChecked = false;
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
    
    public boolean isCheckMated(){
        boolean checkmated = true;
        
        /* pseudo code
        //call isValidMove for this player's king on all index's +/- 9 , just a 
        //quick lazy way to see if it can move anywhere
        for(int i = this.positionOfKing; i < this.positionOfKing + 9; i++){
            if(this.King.isValidMove(i){
                
                //update king's location on board, remove pieces it can take
                int kingOldPosition = this.positionOfKing;
                ChessBoard.getIndex(i).setBoardPosition(some dummy index?);
                this.King.setBoardPosition(i);
                
                if(this.isChecked() == false){
                    checkmated = false;       
                    //put king back to it's original location, put pieces it took
                    //back to there's
                    this.King.setBoardPosition(kingOldPosition);
                    ChessBoard.getIndex(dummy index).setBoardPosition(i);
                    break;
                }
            }
        }
        
        //cycle through all of this player's color's pieces, simulate their moves
        //see if they're still in check.....
        
        */
        
        return checkmated;
    }
    
    public void setIsChecked(boolean check){
        this.isChecked = check;
    }
    
}
