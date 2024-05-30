package org.example.endc;



import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionManager {
    private PublicKey publicKey;

    private static final String PUBLIC_KEY_STRING = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkbS6elMP1as+NDGd1W+g6InpXRe/gCptnehDrsSxd2THEuiPIyH/sfNLNgN93juQag5itLzP5hOeWsDPv3yqLHvEiyQUUyn+Sdg9SWKA6yTSNVPE2t6ftjpwhvBeeAhzQbApHoM07i+nd2x3XAjpS5mfcxGoRQDA+zpAJ1oFogy/0FPtf0lASUfd0Acz1nA62ZrR+R2xhNGYdbzKXFSXY8OeEKK6eLp+T5hsQK/KuLuHURSv82FZAFN6GfauqEw6Ca424UZQ/llRE2kFgrixLFkbm1HJaZu8pCmirDkQDX6khxiDby4UE1X018E4kISPyqfxSpxZt0JghxigSsxycQIDAQAB";


    public void initFromStrings(){
        try{
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpecPublic);
        }catch (Exception ignored){}
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String encrypt(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }
}
