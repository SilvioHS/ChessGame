/*
 * Player Class
 */


package chessgame;

import mousemovement.DragPieceListener;
import chesspieces.ChessPiece;
import chesspieces.Bishop;
import chesspieces.King;
import chesspieces.Knight;
import chesspieces.Pawn;
import chesspieces.Queen;
import chesspieces.Rook;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 *
 * @author Nick Ottrando
 */
public class Player {
    private Image pawn_w, rook_w, knight_w, bishop_w, queen_w, king_w, pawn_b, rook_b,
         knight_b, bishop_b, queen_b, king_b;
    private String color;
    private boolean isTurn;
    private boolean isChecked;
    private boolean isCheckmated;
    //make it easy to keep track of king's position on board, if we do not have
    //each player have own array of pieces
   private int positionOfKing;
   private ChessGame game;
    
   /**
    * <p>
     * Construct Player object give a color, ChessBoard, and ChessGame
     * <p>
     * 
     * <p>
     * Player will set up this Player's pieces in the 1D ChessBoard with all of the correct
     * starting location given this Player's color. Will also set up the DragPieceListener 
     * for each of this Player's pieces.
     * <p>
     * 
     * 
    * @param color The color of this Player
    * @param board The ChessBoard to add this Player's ChessPieces to 
    * @param game ChessGame object which will be set up the DragPieceListener for 
    * each of this Player's pieces 
    */
    public Player(String color,JLayeredPane board,ChessGame game)
    {
        String white = new String();
        white = "white";
        String black = new String();
        black = "black";
        this.color = color;
        this.game = game;
        try {
            if (color.equals("white")) {
                
                positionOfKing = 4;
                
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

                board.add(pawn,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn2,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn3,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn4,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn5,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn6,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn7,JLayeredPane.DEFAULT_LAYER);
                board.add(pawn8,JLayeredPane.DEFAULT_LAYER);
                board.add(rook1,JLayeredPane.DEFAULT_LAYER);
                board.add(knight1,JLayeredPane.DEFAULT_LAYER);
                board.add(bishop1,JLayeredPane.DEFAULT_LAYER);
                board.add(queen,JLayeredPane.DEFAULT_LAYER);
                board.add(king,JLayeredPane.DEFAULT_LAYER);
                board.add(bishop2,JLayeredPane.DEFAULT_LAYER);
                board.add(knight2,JLayeredPane.DEFAULT_LAYER);
                board.add(rook2,JLayeredPane.DEFAULT_LAYER);

                game.setChessLocation(pawn, 8, 88);
                game.setChessLocation(pawn2, 88, 88);
                game.setChessLocation(pawn3, 168, 88);
                game.setChessLocation(pawn4, 248, 88);
                game.setChessLocation(pawn5, 328, 88);
                game.setChessLocation(pawn6, 408, 88);
                game.setChessLocation(pawn7, 488, 88);
                game.setChessLocation(pawn8, 568, 88);

                game.setChessLocation(rook1, 8, 8);
                game.setChessLocation(knight1, 88, 8);
                game.setChessLocation(bishop1, 168, 8);
                game.setChessLocation(queen, 248, 8);
                game.setChessLocation(king, 328, 8);
                game.setChessLocation(bishop2, 408, 8);
                game.setChessLocation(knight2, 488, 8);
                game.setChessLocation(rook2, 568, 8);

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
                
                positionOfKing = 60;
                
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

                board.add(b_pawn,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn2,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn3,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn4,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn5,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn6,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn7,JLayeredPane.DEFAULT_LAYER);
                board.add(b_pawn8,JLayeredPane.DEFAULT_LAYER);
                board.add(b_rook1,JLayeredPane.DEFAULT_LAYER);
                board.add(b_knight1,JLayeredPane.DEFAULT_LAYER);
                board.add(b_bishop1,JLayeredPane.DEFAULT_LAYER);
                board.add(b_queen,JLayeredPane.DEFAULT_LAYER);
                board.add(b_king,JLayeredPane.DEFAULT_LAYER);
                board.add(b_bishop2,JLayeredPane.DEFAULT_LAYER);
                board.add(b_knight2,JLayeredPane.DEFAULT_LAYER);
                board.add(b_rook2,JLayeredPane.DEFAULT_LAYER);

                game.setChessLocation(b_pawn, 8, 488);
                game.setChessLocation(b_pawn2, 88, 488);
                game.setChessLocation(b_pawn3, 168, 488);
                game.setChessLocation(b_pawn4, 248, 488);
                game.setChessLocation(b_pawn5, 328, 488);
                game.setChessLocation(b_pawn6, 408, 488);
                game.setChessLocation(b_pawn7, 488, 488);
                game.setChessLocation(b_pawn8, 568, 488);

                game.setChessLocation(b_rook1, 8, 568);
                game.setChessLocation(b_knight1, 88, 568);
                game.setChessLocation(b_bishop1, 168, 568);
                game.setChessLocation(b_queen, 248, 568);
                game.setChessLocation(b_king, 328, 568);
                game.setChessLocation(b_bishop2, 408, 568);
                game.setChessLocation(b_knight2, 488, 568);
                game.setChessLocation(b_rook2, 568, 568);

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
    
    /**
     * <p>
     * Set whether it is this Player's turn or not 
     * <p>
     * 
     * @param turn The new value of the isTurn flag
     */
    public void setIsTurn(boolean turn){
        this.isTurn = turn;
    }
    
    /**
     * 
     * @return True if it is the turn of this Player, false otherwise 
     */
    public boolean getIsTurn(){
        return this.isTurn;
    }
    
    /**
     * 
     * @return The color of this Player
     */
    public String getColor(){
        return this.color;
    }
    
    /**
     * 
     * @return The e of this Player's King 
     */
    public int getPositionOfKing(){
        return this.positionOfKing;
    }
    
    /**
     * 
     * @param positionOfKing The new position of this Player's King 
     */
    public void setPositionOfKing(int positionOfKing){
        this.positionOfKing = positionOfKing; 
    }
    
    /**
     * <p>
     * This Player is in check when it's King is under attack by at least one enemy piece. 
     * A player may not make any move which places or leaves his King in check. The possible ways to get out of check are:
     * <p>
     * 
     * <p>
     * 1. Move the King to a square where it is not threatened.
     * 2. Capture the threatening piece (possibly with the King).
     * 3. Block the check by placing a piece between the King and the opponent's threatening piece
     * <p>
     * 
     * @return True if this Player is in check, otherwise return false
     */
   public boolean isChecked(){
        
        //index is piece that get's 'taken' in simulation for isChecked
        
        this.isChecked = false;
        //pseduocode
        //this is not as efficient as having each player have their own separate
        //arrayLists of pieces but that may be a hassle to keep track of/update.
        
        //0 to 63 don't want to check dummy index 
        for(int i = 0; i < ChessBoard.pieces.length-1; i++){
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
    
   /**
    * <p>
    * If this Player's King is placed in check and there is no legal move that this Player can make to escape check, 
    * then this is Player's King is said to be checkmated, the game ends, and this Player loses  
    * Unlike other pieces, the King is never actually captured or removed from the board because checkmate ends the game
    * <p>
    * 
    * @return True if this Player is checkmated, otherwise return false 
    */
    public boolean isCheckMated(){
        boolean checkmated = true;
        boolean occupiedSquare = false;

        
           
        
        //cycle through all of this player's color's pieces, simulate their moves
        //see if they're still in check.....
        
        
        for (ChessPiece piece : ChessBoard.pieces) {
            if(piece != null && piece.getColor().equals(this.getColor())){
                for(int i = 0; i < ChessBoard.pieces.length-1; i++){
                    if(piece.isValidMove(i)){
                        int oldIndex = piece.getBoardPosition();

                        //if they move to a square that's occupied by opponent piece, need to simulate 'taking'
                        if(ChessBoard.pieces[i] != null && !ChessBoard.pieces[i].getColor().equals(this.getColor())){
                            occupiedSquare = true;
                            ChessBoard.pieces[oldIndex].setBoardPosition(65);
                            ChessBoard.pieces[65] = ChessBoard.pieces[i];
                            
                        }
                        //move this piece to new square
                        piece.setBoardPosition(i);
                        ChessBoard.pieces[i] = piece;
                        if(piece instanceof King){
                            this.positionOfKing = i;
                        }                    
                        
                        //see if this move will uncheck them
                        if(!this.isChecked()){
                            checkmated = false;
                            if(piece instanceof King){
                                this.positionOfKing = oldIndex;
                            }
                            System.out.println("Piece at index " + oldIndex + " can uncheck you");
                            if(occupiedSquare){
                                //put the pieces back
                                ChessBoard.pieces[i].setBoardPosition(oldIndex);
                                ChessBoard.pieces[oldIndex] = ChessBoard.pieces[i];                     
                                ChessBoard.pieces[65].setBoardPosition(i);
                                ChessBoard.pieces[i] = ChessBoard.pieces[65];  
                                ChessBoard.pieces[65] = null;
                                
                            }else{
                                ChessBoard.pieces[i].setBoardPosition(oldIndex);
                                ChessBoard.pieces[oldIndex] = ChessBoard.pieces[i];
                                ChessBoard.pieces[i] = null;
                            }
                            
                            break;
                        }else{
                            if(piece instanceof King){
                                this.positionOfKing = oldIndex;
                            }
                            //put the pieces back
                            if(occupiedSquare){
                                ChessBoard.pieces[i].setBoardPosition(oldIndex);
                                ChessBoard.pieces[oldIndex] = ChessBoard.pieces[i];                     
                                ChessBoard.pieces[65].setBoardPosition(i);
                                ChessBoard.pieces[i] = ChessBoard.pieces[65];  
                                ChessBoard.pieces[65] = null;
                            }else{
                                ChessBoard.pieces[oldIndex] = piece;
                                ChessBoard.pieces[oldIndex].setBoardPosition(oldIndex);
                                ChessBoard.pieces[i] = null;
                            }
                        }
                    }
                }
            }
        }
        
        
        if(checkmated){
            System.out.println(this.getColor() + " has been CHECKMATED!");
            game.getGameLog().logText(this.getColor() + " has been Checkmated");
        }
        
        
        return checkmated;
    }
    
    
    private void setIsChecked(boolean check){
        this.isChecked = check;
    }
    
}
