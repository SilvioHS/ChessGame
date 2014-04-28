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
    
    public boolean isChecked(int index){
        
        //index is piece that get's 'taken' in simulation for isChecked
        
        this.isChecked = false;
        //pseduocode
        //this is not as efficient as having each player have their own separate
        //arrayLists of pieces but that may be a hassle to keep track of/update.
        
        for(int i = 0; i < ChessBoard.pieces.length; i++){
            //these can be combined into 1 if but it's long so i broke into 2
            if (i != index && ChessBoard.getIndex(i) != null && ChessBoard.getIndex(i).getColor().equals(this.getColor()) == false){        
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
        boolean occupiedSquare = false;
        
        /* pseudo code
        //call isValidMove for this player's king on all index's +/- 9 , just a 
        //quick lazy way to see if it can move anywhere
        for(int i = this.positionOfKing; i < this.positionOfKing + 9; i++){
            if(this.King.isValidMove(i){
                
                //update king's location on board, remove pieces it can take
                if(ChessBoard.pieces[i] != null && ChessBoard.pieces[i].getColor() != this.getColor())
                    oldPiece = ChessBoard.pieces[i];
                    boolean occupiedSquare = true;
                }
                ChessBoard.pieces[i] = this.King;
                             
                if(this.isChecked() == false){
                    checkmated = false;       
                    //put king back to it's original location, put pieces it took
                    //back to there's
                    ChessBoard[this.positionOfKing] = this.King;
                    if(occupiedSquare){
                        ChessBoard[i] = oldPiece;
                    }
                    break;
                }else{
                    //need to put them back anyway
                    ChessBoard[this.positionOfKing] = this.King;
                    if(occupiedSquare){
                        ChessBoard[i] = oldPiece;
                    }
                }
            }
        }
        
        //cycle through all of this player's color's pieces, simulate their moves
        //see if they're still in check.....
        
        //does this work? netbeans recommend it "enhanced loop" apparently it does
        // https://blogs.oracle.com/CoreJavaTechTips/entry/using_enhanced_for_loops_with
        
        //new idea 4/28: give isChecked a parameter index of whatever piece gets
        //'taken' in the simulation, exclude checking that piece in the isChecked() method
        
        for (ChessPiece piece : ChessBoard.pieces) {
            if(piece.getColor().equals(this.getColor())){
                for(int i = 0; i < ChessBoard.pieces.length; i++){
                    if(piece.isValidMove(i)){
                        int oldIndex = i;
                        
                        //this might need to be a deep copy? or something
                        //copy old piece before replacing
                        if(ChessBoard.pieces[i] != null && ChessBoard.pieces[i].getColor() != this.getColor()){
                            ChessPiece oldPiece = ChessBoard.pieces[i];
                            occupiedSquare = true;
                        }
                        //move this piece to new square
                        ChessBoard.pieces[i] = piece;
                        
                        if(!this.isChecked){
                            checkmated = false;
                            if(occupiedSquare){
                                //put the pieces back
                                ChessBoard.pieces[i] = oldPiece;
                                ChessBoard.pieces[oldIndex] = piece;
                            }
                            
                            break;
                        }else{
                            //put the pieces back
                            if(occupiedSquare){
                                ChessBoard.pieces[i] = oldPiece;
                                ChessBoard.pieces[oldIndex] = piece;
                            }
                        }
                    }
                }
            }
        }
        
        */
        
        
        
        return checkmated;
    }
    
    public void setIsChecked(boolean check){
        this.isChecked = check;
    }
    
}
