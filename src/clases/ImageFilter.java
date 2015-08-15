package frames;



import java.io.File;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter { //tipos de archivos de imagen para el memorama
    final static String jpeg = "jpeg";
    final static String jpg = "jpg";
    final static String gif = "gif";
    final static String bmp = "bmp";
    final static String png = "png";
    
    // Accept all directories and all gif, jpg, or tiff files.
    public boolean accept(File f) {


        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (gif.equals(extension) || jpeg.equals(extension) || jpg.equals(extension) || bmp.equals(extension) || png.equals(extension)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
    
    // The description of this filter
    public String getDescription() {
        return "Solo Imagenes";
    }
}
