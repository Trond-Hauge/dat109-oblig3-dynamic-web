package qr;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import stand.Stand;

public class MyQr {
	
	// Temporary code that assumes what DAO for Stand-object will look like.
	//private static StandDAO sd = new StandDAO();
	 
    // Function to create the QR code
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
   
    // Driver code
    public static void main(String[] args) throws WriterException, IOException, NotFoundException{
    	
        // The data that the QR code will contain
    	// Will concatenate corresponding ID
        String data = "www.ourApplication.com?";
 
        // The path where the image will get saved, the QR Codes folder under the src folder.
        String path = "QR Codes/";
 
        // Encoding charset
        String charset = "UTF-8";
        
        // List of stands to generate QR codes for.
        // Temporary code that assumes StandService will have a get-method for all stands.
        List<Stand> stands = sd.getAllStands();
 
        for(Stand s: stands) {
        	
        	Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            	= new HashMap<EncodeHintType, ErrorCorrectionLevel>();
 
	        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	 
	        String id = s.getId();
	        
	        // Create the QR code and save in the specified folder as a jpg file
	        createQR(data + "id=" + id, path + "stand" + id, charset, hashMap, 200, 200);
        }
    }
}
    
