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
    
    /**
     * <p>
     * Constructs a Coordinate object. Creates coordinates of each ChessBooard square, 
     * each ChessPiece, and each ChessPiece center
     * <p>
     * 
     */
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
    
    /**
     * 
     * @param index The index of the given ChessBoard square
     * @return The X coordinate of the ChessBoard square
     */
    public int getBoardX(int index){
        return this.boardX[index];
    }
    
    /**
     * 
     * @param index The index of the given ChessBoard square
     * @return The Y coordinate of the ChessBoard square
     */
    public int getBoardY(int index){
        return this.boardY[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The X coordinate of the ChessPiece 
     */
    public int getPieceX(int index){
        return this.pieceX[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The Y coordinate of the ChessPiece 
     */
    public int getPieceY(int index){
        return this.pieceY[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The X coordinate of the center of the ChessPiece 
     */
    public int getPieceCenterX(int index){
        return this.pieceCenterX[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The Y coordinate of the center of the ChessPiece 
     */
    public int getPieceCenterY(int index){
        return this.pieceCenterY[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The x coordinate of the white ChessPiece that is being taken 
     */
    public int getWhiteX(int index){
        return this.whiteX[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The y coordinate of the white ChessPiece that is being taken
     */
    public int getWhiteY(int index){
        return this.whiteY[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The x coordinate of the black ChessPiece that is being taken
     */
    public int getBlackX(int index){
        return this.blackX[index];
    }
    
    /**
     * 
     * @param index The position of the ChessPiece 
     * @return The y coordinate of the black ChessPiece that is being taken
     */
    public int getBlackY(int index){
        return this.blackY[index];
    }
}
