/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame;

import static chessgame.ChessGame.setChessLocation;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Nick
 */
public class Player {
    public Image pawn_w, rook_w, knight_w, bishop_w, queen_w, king_w, pawn_b, rook_b,
         knight_b, bishop_b, queen_b, king_b;
    String color;
    boolean isTurn;
    boolean isChecked;
    boolean isCheckmated;
    //make it easy to keep track of king's position on board, if we do not have
    //each player have own array of pieces
    int positionOfKing;
    
    public Player(String color,JPanel board,ChessGame game)
    {
        String white = new String();
        white = "white";
        String black = new String();
        black = "black";
        this.color = color;
        try {
            if (color.equals("white")) {
                pawn_w = ImageIO.read(new File("src/image/pawn_white.png"));
                rook_w = ImageIO.read(new File("src/image/rook_white.png"));
                knight_w = ImageIO.read(new File("src/image/knight_white.png")); //spelling....
                bishop_w = ImageIO.read(new File("src/image/bishop_white.png"));
                queen_w = ImageIO.read(new File("src/image/queen_white.png"));
                king_w = ImageIO.read(new File("src/image/king_white.png"));
                Pawn pawn = new Pawn(pawn_w, white, 8);//Just for testing
                ChessBoard.pieces[8] = pawn;
                Pawn pawn2 = new Pawn(pawn_w, white, 9);
                ChessBoard.pieces[9] = pawn2;
                Pawn pawn3 = new Pawn(pawn_w, white, 10);
                ChessBoard.pieces[10] = pawn3;
                Pawn pawn4 = new Pawn(pawn_w, white, 11);
                ChessBoard.pieces[11] = pawn4;
                Pawn pawn5 = new Pawn(pawn_w, white, 12);
                ChessBoard.pieces[12] = pawn5;
                Pawn pawn6 = new Pawn(pawn_w, white, 13);
                ChessBoard.pieces[13] = pawn6;
                Pawn pawn7 = new Pawn(pawn_w, white, 14);
                ChessBoard.pieces[14] = pawn7;
                Pawn pawn8 = new Pawn(pawn_w, white, 15);
                ChessBoard.pieces[15] = pawn8;
                Rook rook1 = new Rook(rook_w, white, 0);
                ChessBoard.pieces[0] = rook1;
                Knight knight1 = new Knight(knight_w, white, 1);
                ChessBoard.pieces[1] = knight1;
                Bishop bishop1 = new Bishop(bishop_w, white, 2);
                ChessBoard.pieces[2] = bishop1;
                Queen queen = new Queen(queen_w, white, 3);
                ChessBoard.pieces[3] = queen;
                King king = new King(king_w, white, 4);
                ChessBoard.pieces[4] = king;
                Bishop bishop2 = new Bishop(bishop_w, white, 5);
                ChessBoard.pieces[5] = bishop2;
                Knight knight2 = new Knight(knight_w, white, 6);
                ChessBoard.pieces[6] = knight2;
                Rook rook2 = new Rook(rook_w, white, 7);
                ChessBoard.pieces[7] = rook2;

                board.add(pawn);
                board.add(pawn2);
                board.add(pawn3);
                board.add(pawn4);
                board.add(pawn5);
                board.add(pawn6);
                board.add(pawn7);
                board.add(pawn8);
                board.add(rook1);
                board.add(knight1);
                board.add(bishop1);
                board.add(queen);
                board.add(king);
                board.add(bishop2);
                board.add(knight2);
                board.add(rook2);

                ChessGame.setChessLocation(pawn, 8, 88);
                ChessGame.setChessLocation(pawn2, 88, 88);
                ChessGame.setChessLocation(pawn3, 168, 88);
                ChessGame.setChessLocation(pawn4, 248, 88);
                ChessGame.setChessLocation(pawn5, 328, 88);
                ChessGame.setChessLocation(pawn6, 408, 88);
                ChessGame.setChessLocation(pawn7, 488, 88);
                ChessGame.setChessLocation(pawn8, 568, 88);

                ChessGame.setChessLocation(rook1, 8, 8);
                ChessGame.setChessLocation(knight1, 88, 8);
                ChessGame.setChessLocation(bishop1, 168, 8);
                ChessGame.setChessLocation(queen, 248, 8);
                ChessGame.setChessLocation(king, 328, 8);
                ChessGame.setChessLocation(bishop2, 408, 8);
                ChessGame.setChessLocation(knight2, 488, 8);
                ChessGame.setChessLocation(rook2, 568, 8);

                DragPieceListener WhitePawnlistener = new DragPieceListener(game);  //white MouseListener
                DragPieceListener WhitePawnlistener2 = new DragPieceListener(game);
                DragPieceListener WhitePawnlistener3 = new DragPieceListener(game);
                DragPieceListener WhitePawnlistener4 = new DragPieceListener(game);
                DragPieceListener WhitePawnlistener5 = new DragPieceListener(game);
                DragPieceListener WhitePawnlistener6 = new DragPieceListener(game);
                DragPieceListener WhitePawnlistener7 = new DragPieceListener(game);
                DragPieceListener WhitePawnlistener8 = new DragPieceListener(game);
                DragPieceListener WhiteRooklistener = new DragPieceListener(game);
                DragPieceListener WhiteKnightlistener = new DragPieceListener(game);
                DragPieceListener WhiteBishoplistener = new DragPieceListener(game);
                DragPieceListener WhiteQueenlistener = new DragPieceListener(game);
                DragPieceListener WhiteKinglistener = new DragPieceListener(game);
                DragPieceListener WhiteBishoplistener2 = new DragPieceListener(game);
                DragPieceListener WhiteKnightlistener2 = new DragPieceListener(game);
                DragPieceListener WhiteRooklistener2 = new DragPieceListener(game);
                pawn.addMouseListener(WhitePawnlistener);  // white add mouselistener to image
                pawn.addMouseMotionListener(WhitePawnlistener);
                pawn2.addMouseListener(WhitePawnlistener2);
                pawn2.addMouseMotionListener(WhitePawnlistener2);
                pawn3.addMouseListener(WhitePawnlistener3);
                pawn3.addMouseMotionListener(WhitePawnlistener3);
                pawn4.addMouseListener(WhitePawnlistener4);
                pawn4.addMouseMotionListener(WhitePawnlistener4);
                pawn5.addMouseListener(WhitePawnlistener5);
                pawn5.addMouseMotionListener(WhitePawnlistener5);
                pawn6.addMouseListener(WhitePawnlistener6);
                pawn6.addMouseMotionListener(WhitePawnlistener6);
                pawn7.addMouseListener(WhitePawnlistener7);
                pawn7.addMouseMotionListener(WhitePawnlistener7);
                pawn8.addMouseListener(WhitePawnlistener8);
                pawn8.addMouseMotionListener(WhitePawnlistener8);
                rook1.addMouseListener(WhiteRooklistener);
                rook1.addMouseMotionListener(WhiteRooklistener);
                rook2.addMouseListener(WhiteRooklistener2);
                rook2.addMouseMotionListener(WhiteRooklistener2);
                knight1.addMouseListener(WhiteKnightlistener);
                knight1.addMouseMotionListener(WhiteKnightlistener);
                knight2.addMouseListener(WhiteKnightlistener2);
                knight2.addMouseMotionListener(WhiteKnightlistener2);
                bishop1.addMouseListener(WhiteBishoplistener);
                bishop1.addMouseMotionListener(WhiteBishoplistener);
                bishop2.addMouseListener(WhiteBishoplistener2);
                bishop2.addMouseMotionListener(WhiteBishoplistener2);
                king.addMouseListener(WhiteKinglistener);
                king.addMouseMotionListener(WhiteKinglistener);
                queen.addMouseListener(WhiteQueenlistener);
                queen.addMouseMotionListener(WhiteQueenlistener);

            }
            if (color.equals("black")) {
                pawn_b = ImageIO.read(new File("src/image/pawn_black.png"));
                rook_b = ImageIO.read(new File("src/image/rook_black.png"));
                knight_b = ImageIO.read(new File("src/image/knight_black.png")); //spelling....
                bishop_b = ImageIO.read(new File("src/image/bishop_black.png"));
                queen_b = ImageIO.read(new File("src/image/queen_black.png"));
                king_b = ImageIO.read(new File("src/image/king_black.png"));
                Pawn b_pawn = new Pawn(pawn_b, black, 48);//Just for testing
                ChessBoard.pieces[48] = b_pawn;
                Pawn b_pawn2 = new Pawn(pawn_b, black, 49);
                ChessBoard.pieces[49] = b_pawn2;
                Pawn b_pawn3 = new Pawn(pawn_b, black, 50);
                ChessBoard.pieces[50] = b_pawn3;
                Pawn b_pawn4 = new Pawn(pawn_b, black, 51);
                ChessBoard.pieces[51] = b_pawn4;
                Pawn b_pawn5 = new Pawn(pawn_b, black, 52);
                ChessBoard.pieces[52] = b_pawn5;
                Pawn b_pawn6 = new Pawn(pawn_b, black, 53);
                ChessBoard.pieces[53] = b_pawn6;
                Pawn b_pawn7 = new Pawn(pawn_b, black, 54);
                ChessBoard.pieces[54] = b_pawn7;
                Pawn b_pawn8 = new Pawn(pawn_b, black, 55);
                ChessBoard.pieces[55] = b_pawn8;

                Rook b_rook1 = new Rook(rook_b, black, 56);
                ChessBoard.pieces[56] = b_rook1;
                Knight b_knight1 = new Knight(knight_b, black, 57);
                ChessBoard.pieces[57] = b_knight1;
                Bishop b_bishop1 = new Bishop(bishop_b, black, 58);
                ChessBoard.pieces[58] = b_bishop1;
                Queen b_queen = new Queen(queen_b, black, 59);
                ChessBoard.pieces[59] = b_queen;
                King b_king = new King(king_b, black, 60);
                ChessBoard.pieces[60] = b_king;
                Bishop b_bishop2 = new Bishop(bishop_b, black, 61);
                ChessBoard.pieces[61] = b_bishop2;
                Knight b_knight2 = new Knight(knight_b, black, 62);
                ChessBoard.pieces[62] = b_knight2;
                Rook b_rook2 = new Rook(rook_b, black, 63);
                ChessBoard.pieces[63] = b_rook2;

                board.add(b_pawn);
                board.add(b_pawn2);
                board.add(b_pawn3);
                board.add(b_pawn4);
                board.add(b_pawn5);
                board.add(b_pawn6);
                board.add(b_pawn7);
                board.add(b_pawn8);
                board.add(b_rook1);
                board.add(b_knight1);
                board.add(b_bishop1);
                board.add(b_queen);
                board.add(b_king);
                board.add(b_bishop2);
                board.add(b_knight2);
                board.add(b_rook2);

                setChessLocation(b_pawn, 8, 488);
                setChessLocation(b_pawn2, 88, 488);
                setChessLocation(b_pawn3, 168, 488);
                setChessLocation(b_pawn4, 248, 488);
                setChessLocation(b_pawn5, 328, 488);
                setChessLocation(b_pawn6, 408, 488);
                setChessLocation(b_pawn7, 488, 488);
                setChessLocation(b_pawn8, 568, 488);

                setChessLocation(b_rook1, 8, 568);
                setChessLocation(b_knight1, 88, 568);
                setChessLocation(b_bishop1, 168, 568);
                setChessLocation(b_queen, 248, 568);
                setChessLocation(b_king, 328, 568);
                setChessLocation(b_bishop2, 408, 568);
                setChessLocation(b_knight2, 488, 568);
                setChessLocation(b_rook2, 568, 568);

                DragPieceListener BlackPawnlistener = new DragPieceListener(game);  //black MouseListener
                DragPieceListener BlackPawnlistener2 = new DragPieceListener(game);
                DragPieceListener BlackPawnlistener3 = new DragPieceListener(game);
                DragPieceListener BlackPawnlistener4 = new DragPieceListener(game);
                DragPieceListener BlackPawnlistener5 = new DragPieceListener(game);
                DragPieceListener BlackPawnlistener6 = new DragPieceListener(game);
                DragPieceListener BlackPawnlistener7 = new DragPieceListener(game);
                DragPieceListener BlackPawnlistener8 = new DragPieceListener(game);
                DragPieceListener BlackRooklistener = new DragPieceListener(game);
                DragPieceListener BlackKnightlistener = new DragPieceListener(game);
                DragPieceListener BlackBishoplistener = new DragPieceListener(game);
                DragPieceListener BlackQueenlistener = new DragPieceListener(game);
                DragPieceListener BlackKinglistener = new DragPieceListener(game);
                DragPieceListener BlackBishoplistener2 = new DragPieceListener(game);
                DragPieceListener BlackKnightlistener2 = new DragPieceListener(game);
                DragPieceListener BlackRooklistener2 = new DragPieceListener(game);

                b_pawn.addMouseListener(BlackPawnlistener);  // Black add mouselistener to image
                b_pawn.addMouseMotionListener(BlackPawnlistener);
                b_pawn2.addMouseListener(BlackPawnlistener2);
                b_pawn2.addMouseMotionListener(BlackPawnlistener2);
                b_pawn3.addMouseListener(BlackPawnlistener3);
                b_pawn3.addMouseMotionListener(BlackPawnlistener3);
                b_pawn4.addMouseListener(BlackPawnlistener4);
                b_pawn4.addMouseMotionListener(BlackPawnlistener4);
                b_pawn5.addMouseListener(BlackPawnlistener5);
                b_pawn5.addMouseMotionListener(BlackPawnlistener5);
                b_pawn6.addMouseListener(BlackPawnlistener6);
                b_pawn6.addMouseMotionListener(BlackPawnlistener6);
                b_pawn7.addMouseListener(BlackPawnlistener7);
                b_pawn7.addMouseMotionListener(BlackPawnlistener7);
                b_pawn8.addMouseListener(BlackPawnlistener8);
                b_pawn8.addMouseMotionListener(BlackPawnlistener8);
                b_rook1.addMouseListener(BlackRooklistener);
                b_rook1.addMouseMotionListener(BlackRooklistener);
                b_rook2.addMouseListener(BlackRooklistener2);
                b_rook2.addMouseMotionListener(BlackRooklistener2);
                b_knight1.addMouseListener(BlackKnightlistener);
                b_knight1.addMouseMotionListener(BlackKnightlistener);
                b_knight2.addMouseListener(BlackKnightlistener2);
                b_knight2.addMouseMotionListener(BlackKnightlistener2);
                b_bishop1.addMouseListener(BlackBishoplistener);
                b_bishop1.addMouseMotionListener(BlackBishoplistener);
                b_bishop2.addMouseListener(BlackBishoplistener2);
                b_bishop2.addMouseMotionListener(BlackBishoplistener2);
                b_king.addMouseListener(BlackKinglistener);
                b_king.addMouseMotionListener(BlackKinglistener);
                b_queen.addMouseListener(BlackQueenlistener);
                b_queen.addMouseMotionListener(BlackQueenlistener);

            }
        } catch (IOException e) {
        }
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
        
        //int oldKingPos;
        
        for(int i = this.positionOfKing; i < this.positionOfKing + 9; i++){
            if(this.King.isValidMove(i){
                
                //update king's location on board, remove pieces it can take
                if(ChessBoard.pieces[i] != null && ChessBoard.pieces[i].getColor() != this.getColor())
                    oldPiece = ChessBoard.pieces[i];
                    boolean occupiedSquare = true;
                }
                ChessBoard.pieces[i] = this.King;
                oldKingPos = this.positionOfKing; 
                this.positionOfKing = i;
                             
                if(this.isChecked() == false){
                    checkmated = false;       
                    //put king back to it's original location, put pieces it took
                    //back to there's
                    ChessBoard[oldKingPos] = this.King;
                    if(occupiedSquare){
                        ChessBoard[i] = oldPiece;
                    }
                    break;
                }else{
                    //need to put them back anyway
                    ChessBoard[oldKingPos] = this.King;
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
