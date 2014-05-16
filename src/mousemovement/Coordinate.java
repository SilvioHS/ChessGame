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
    private int[] whiteX = new int[16];
    private int[] whiteY = new int[16];
    private int[] blackX = new int[16];
    private int[] blackY = new int[16];
    
    public Coordinate(){
        for(int i=0; i<64; i++){
            this.boardX[i] = i%8*80;
            this.boardY[i] = i/8*80;
            this.pieceX[i] = i%8*80+8;
            this.pieceY[i] = i/8*80+8;
            this.pieceCenterX[i] = i%8*80+40;
            this.pieceCenterY[i] = i/8*80+40;
        }
        
         for(int i=0;i<16;i++){
            if(i<8){
                this.whiteX[i] =660 + i*50;
                this.whiteY[i] =50;
                this.blackX[i] =660 + i*50;
                this.blackY[i] =200;
            }
            else{
                this.whiteX[i] =260 + i*50;
                this.whiteY[i] =100;
                this.blackX[i] =260 + i*50;
                this.blackY[i] =250;
            }
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
    
    public int getWhiteX(int index){
        return this.whiteX[index];
    }
    public int getWhiteY(int index){
        return this.whiteY[index];
    }
    public int getBlackX(int index){
        return this.blackX[index];
    }
    public int getBlackY(int index){
        return this.blackY[index];
    }
}
