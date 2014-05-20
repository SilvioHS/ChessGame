/*
 * Class to allow users to save games in progress.
 */


package menu;

import chessgame.ChessBoard;
import chesspieces.ChessPiece;
import chesspieces.Bishop;
import chesspieces.King;
import chesspieces.Knight;
import chesspieces.Pawn;
import chesspieces.Queen;
import chesspieces.Rook;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SaveGame {
    private final File saveFile;
    private BufferedWriter saveWriter;
    private final ChessBoard chessboard;
    private ChessPiece cp;
    private final GameLog gamelog;
    private String turn;
    
    /**
     * 
     * Constructs a SaveGame object. 
     * <p>
     * 
     *<p>
     * Save what is in each square on the ChessBoard, if there is nothing there, save as 0,
     * if it is a piece, save as a letter code. Save current players turn and save all text in GameLog
     *<p>
     * 
     * @param f The File to be saved to 
     * @param cb The ChessBoard to save the ChessPieces to File
     * @param gl The GameLog to save the ext from to the File
     * @param t The color of the color who's turn it is to move 
     */
    public SaveGame(File f, ChessBoard cb, GameLog gl, String t){
        saveFile = f;
        chessboard = cb;
        gamelog = gl;
        turn = t;
        saveToFile();
    }
   
    private void saveToFile() {
        try {
            saveWriter = new BufferedWriter(new FileWriter(saveFile));
            for (int i = 0; i < 64; i++) {
                cp = chessboard.getIndex(i);
                
                if (cp == null) {
                    saveWriter.write("0");
                    saveWriter.newLine();
                    
                    
                } else if ((cp instanceof Pawn) && (cp.getColor().equals("white"))) {
                    saveWriter.write("wp");
                    saveWriter.newLine();
                } else if ((cp instanceof Rook) && (cp.getColor().equals("white"))) {
                    saveWriter.write("wr");
                    saveWriter.newLine();
                } else if ((cp instanceof Knight) && (cp.getColor().equals("white"))) {
                    saveWriter.write("wn");
                    saveWriter.newLine();
                } else if ((cp instanceof Bishop) && (cp.getColor().equals("white"))) {
                    saveWriter.write("wb");
                    saveWriter.newLine();
                } else if ((cp instanceof Queen) && (cp.getColor().equals("white"))) {
                    saveWriter.write("wq");
                    saveWriter.newLine();
                } else if ((cp instanceof King) && (cp.getColor().equals("white"))) {
                    saveWriter.write("wk");
                    saveWriter.newLine();
                    
                    
                } else if ((cp instanceof Pawn) && (cp.getColor().equals("black"))) {
                    saveWriter.write("bp");
                    saveWriter.newLine();
                } else if ((cp instanceof Rook) && (cp.getColor().equals("black"))) {
                    saveWriter.write("br");
                    saveWriter.newLine();
                } else if ((cp instanceof Knight) && (cp.getColor().equals("black"))) {
                    saveWriter.write("bn");
                    saveWriter.newLine();
                } else if ((cp instanceof Bishop) && (cp.getColor().equals("black"))) {
                    saveWriter.write("bb");
                    saveWriter.newLine();
                } else if ((cp instanceof Queen) && (cp.getColor().equals("black"))) {
                    saveWriter.write("bq");
                    saveWriter.newLine();
                } else if ((cp instanceof King) && (cp.getColor().equals("black"))) {
                    saveWriter.write("bk");
                    saveWriter.newLine();
                }
            }
            
            //save turn to file
            saveWriter.write(turn);
            saveWriter.newLine();
            
            //save game log to file
            saveWriter.write(gamelog.getLog());
            
            
        } catch (IOException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally {
            try {
                // Close the writer regardless of what happens...
                saveWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}