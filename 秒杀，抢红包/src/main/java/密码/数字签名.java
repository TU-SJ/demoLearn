package 密码;

import org.apache.commons.codec.binary.Base64;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class 数字签名 {
    private static String secret = "itstyle";

    @Deprecated
    public byte[] eccryptSHA(String msg) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA");
        byte[] srcBytes = msg.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }
    public String eccryptSHA2(String msg) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(msg.getBytes()));
        return hash;
    }
    /**
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String msg = "爪哇笔记";
        数字签名 sha = new 数字签名();
        System.out.println(sha.eccryptSHA2(msg));
    }
}
