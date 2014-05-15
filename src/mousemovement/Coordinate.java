package mousemovement;

/*
 * To provide the Coordinate to ChessGame
 */

/**
 *
 * @author Qilin Fu
 */
public class Coordinate {
    private int[] boardX = new int[64];
    private int[] boardY = new int[64];
    private int[] pieceX = new int[64];
    private int[] pieceY = new int[64];
    private int[] pieceCenterX = new int[64];
    private int[] pieceCenterY = new int[64];
    
    public Coordinate(){
        for(int i=0; i<64; i++){
            this.boardX[i] = i%8*80;
            this.boardY[i] = i/8*80;
            this.pieceX[i] = i%8*80+8;
            this.pieceY[i] = i/8*80+8;
            this.pieceCenterX[i] = i%8*80+40;
            this.pieceCenterY[i] = i/8*80+40;
        }
    }
    
    public int getBoardX(int index){
        return this.boardX[index];
    }
    
    public int getBoardY(int index){
        return this.boardY[index];
    }
    
    public int getPieceX(int index){
        return this.pieceX[index];
    }
    
    public int getPieceY(int index){
        return this.pieceY[index];
    }
    
    public int getPieceCenterX(int index){
        return this.pieceCenterX[index];
    }
    
    public int getPieceCenterY(int index){
        return this.pieceCenterY[index];
    }
}
