/*
 * Class to allow users to save games in progress.
 */


package chessgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Silvio Huebner
 */
public class SaveGame {
    private final File saveFile;
    private BufferedWriter saveWriter;
    private final ChessBoard chessboard;
    private ChessPiece cp;
    
    public SaveGame(File f, ChessBoard cb){
        saveFile = f;
        chessboard = cb;
        saveToFile();
    }
   
    public void saveToFile() {
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
