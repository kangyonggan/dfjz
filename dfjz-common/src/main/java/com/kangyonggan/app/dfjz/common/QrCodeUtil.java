package com.kangyonggan.app.dfjz.common;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码
 *
 * @author kangyonggan
 * @since 4/24/17
 */
public class QrCodeUtil {

    /**
     * 生成二维码
     *
     * @param name
     * @param content
     * @param width
     * @throws WriterException
     * @throws IOException
     */
    public static void genQrCode(String name, String content, int width) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, width, hints);

        Path path = FileSystems.getDefault().getPath(name);
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
    }

    /**
     * qr解码
     *
     * @param url
     * @return
     */
    public static String decode(String url) throws Exception {
        BufferedImage image = ImageIO.read(new URL(url));
        return decode(image);
    }

    /**
     * qr解码
     *
     * @param file
     * @return
     */
    public static String decode(File file) throws Exception {
        BufferedImage image = ImageIO.read(file);
        return decode(image);
    }

    /**
     * qr解码
     *
     * @param input
     * @return
     */
    public static String decode(InputStream input) throws Exception {
        BufferedImage image = ImageIO.read(input);
        return decode(image);
    }

    /**
     * qr解码
     *
     * @param image
     * @return
     * @throws Exception
     */
    private static String decode(BufferedImage image) throws Exception {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Hashtable<DecodeHintType, String> hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }
}
