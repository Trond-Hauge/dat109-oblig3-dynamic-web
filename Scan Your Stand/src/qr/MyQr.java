package qr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import project.Project;

/**
 * 
 * @author anders
 *
 * Class to create QR codes from stands in database 
 */

public class MyQr {

    /**
     * Method used to create QR coded
     * 
     * @param data: the URL
     * @param path: where the URL will be stored
     * @param charset: the charset
     * @param hashMap: certain hashmap needed. 
     * @param height: specify size
     * @param width: specify size
     * @throws WriterException
     * @throws IOException
     */
    public static void createQR(String data, String path,
                                String charset, Map hashMap,
                                int height, int width)
        throws WriterException, IOException
    {
 
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, width, height);
 
        MatrixToImageWriter.writeToPath(
            matrix,
            path.substring(path.lastIndexOf('.') + 1),
            Paths.get(path));
    }
    
    /**
     * Method used for testing
     * 
     * @param qrCodeimage: the QR location
     * @return the URL or null
     * @throws IOException
     */
    public static String QRCodeValidator(File qrCodeimage) throws IOException 
    {
		    BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
	        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

	        try {
	            Result result = new MultiFormatReader().decode(bitmap);
	            return result.getText();
	        } catch (NotFoundException e) {
	            System.out.println("There is no QR code in the image");
	            return null;
 	  }
	}
    
    public static void createQrCodesForProjects(List<Project> projects) {
    	
    	// The data that the QR code will contain
    	// Will concatenate corresponding ID
        String data = "www.ourApplication.com?";
 
        // Encoding charset
        String charset = "UTF-8";
        
        for(Project p: projects) {
        	
        	Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            	= new HashMap<EncodeHintType, ErrorCorrectionLevel>();
	 
	        String id = p.getProjectNumber();
	        
	        String path = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + id + ".png";
	        
	        // Create the QR code and save in the specified folder as a png file
	        try {
				createQR(data + "id=" + id, path, charset, hashMap, 200, 200);
			} catch (WriterException | IOException e) {
				e.printStackTrace();
			}    
        }
    }
}