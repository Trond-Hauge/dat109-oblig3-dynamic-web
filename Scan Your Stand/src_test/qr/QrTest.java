package qr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrTest {

	
	@BeforeEach
	public void setUp() throws WriterException, IOException {
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap
    	= new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		
		MyQr.createQR("www.testing?id=1", "qr-codes/testQR", "UTF-8", hashMap, 200, 200);
	}
	
	@Test
	public void correctURLStored() throws IOException {
		
		File file = new File("qr-codes/testQR");
        MyQr.QRCodeValidator(file);
	}
}
