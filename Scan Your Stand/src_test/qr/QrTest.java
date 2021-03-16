package qr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@TestInstance(Lifecycle.PER_CLASS)
public class QrTest {

    private String URL = "www.testing?id=1";

    @BeforeAll
    public void setUp() throws WriterException, IOException {
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
        = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

        MyQr.createQR(URL, "src/qr/qr-codes/testQR.png", "UTF-8", hashMap, 200, 200);
    }

    @Test
    public void correctURLStored() throws IOException {

        File file = new File("src/qr/qr-codes/testQR.png");
        String readURL = MyQr.QRCodeValidator(file);

        assertEquals(readURL, URL);

    }

    @Test
    public void OnlyCorrectURLStored() throws IOException {

        File file = new File("src/qr/qr-codes/testQR.png");
        String readURL = MyQr.QRCodeValidator(file);

        assertNotEquals(readURL, URL + "2");
        assertNotEquals(readURL, URL.substring(1));
        assertNotEquals(readURL, URL.substring(0, URL.length()-1));
    }
}