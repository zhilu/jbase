package ald.rc.commom.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;

/**
 * 基于codec的工具类
 * @author shiguihong
 * @version 1.0
 */

public class CodecUtil {
    public static final byte [] EMPTY = new byte[0];
    // 字节数组和字符串的转换
    public static byte[] decodeHex(String value)  {
        if(Objects.isNull(value)){
            return EMPTY.clone();
        }
        char [] chars = value.toCharArray();
        if ((chars.length & 0x01) != 0) {
            return EMPTY.clone();
        }
        try {
            return Hex.decodeHex(chars);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return EMPTY.clone();
    }

    public static String encodeHexString(byte [] bytes){
        return Hex.encodeHexString(bytes);
    }

    // base64 转换
    public static String encodeBase64(String value){
       return Base64.encodeBase64String(value.getBytes());
    }
    public static String dncodeBase64(String value){
        return Base64.encodeBase64String(value.getBytes());
    }

    // 消息摘要转换
    public String md5(String value){
        return DigestUtils.md5Hex(value);
    }

    // ==Aes加解密==================================================================
    /**
     * aes解密-128位
     * @param encryptContent 16进制字符串
     */
    public static String AesDecrypt(String encryptContent, String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(decodeHex(encryptContent)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * aes加密-128位
     */
    public static String AesEncrypt(String content, String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return encodeHexString(cipher.doFinal(content.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES加密
     */
    public static String desEncrypt(String source, String desKey) throws Exception {
        try {
            // 从原始密匙数据创建DESKeySpec对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            // 现在，获取数据并加密
            byte[] destBytes = cipher.doFinal(source.getBytes());
            StringBuilder hexRetSB = new StringBuilder();
            for (byte b : destBytes) {
                String hexString = Integer.toHexString(0x00ff & b);
                hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
            }
            return hexRetSB.toString();
        } catch (Exception e) {
            throw new Exception("DES加密发生错误", e);
        }
    }

    /**
     * DES解密
     */
    public static String desDecrypt(String source, String desKey) throws Exception {
        // 解密数据
        byte[] sourceBytes = new byte[source.length() / 2];
        for (int i = 0; i < sourceBytes.length; i++) {
            sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
        }
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(new DESKeySpec(desKey.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            // 现在，获取数据并解密
            byte[] destBytes = cipher.doFinal(sourceBytes);
            return new String(destBytes);
        } catch (Exception e) {
            throw new Exception("DES解密发生错误", e);
        }
    }

    /**
     * 3DES加密
     */
    public static byte[] threeDesEncrypt(byte[] src, byte[] keybyte) throws Exception {
        try {
            // 生成密钥
            byte[] key = new byte[24];
            if (keybyte.length < key.length) {
                System.arraycopy(keybyte, 0, key, 0, keybyte.length);
            } else {
                System.arraycopy(keybyte, 0, key, 0, key.length);
            }
            SecretKey deskey = new SecretKeySpec(key, "DESede");
            // 加密
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (Exception e) {
            throw new Exception("3DES加密发生错误", e);
        }
    }

    /**
     * 3DES解密
     */
    public static byte[] threeDesDecrypt(byte[] src, byte[] keybyte) throws Exception {
        try {
            // 生成密钥
            byte[] key = new byte[24];
            if (keybyte.length < key.length) {
                System.arraycopy(keybyte, 0, key, 0, keybyte.length);
            } else {
                System.arraycopy(keybyte, 0, key, 0, key.length);
            }
            SecretKey deskey = new SecretKeySpec(key, "DESede");
            // 解密
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (Exception e) {
            throw new Exception("3DES解密发生错误", e);
        }
    }

    // ======================================================================================
    // 公私钥算法
    // ======================================================================================
    public final static String ENCODE = "UTF-8";
    public final static String KEY_X509 = "X509";
    public final static String KEY_PKCS12 = "PKCS12";
    public final static String KEY_ALGORITHM = "RSA";
    public final static String CER_ALGORITHM = "MD5WithRSA";
    public final static String RSA_CHIPER = "RSA/ECB/PKCS1Padding";

    public final static int KEY_SIZE = 1024;
    /** 1024bit 加密块 大小 */
    public final static int ENCRYPT_KEYSIZE = 117;
    /** 1024bit 解密块 大小 */
    public final static int DECRYPT_KEYSIZE = 128;
    /**
     * 公钥算法
     *
     * @param srcData
     *            源字节
     * @param Key
     *            公钥 OR 私钥
     * @param mode
     *            加密 OR 解密
     * @return
     */
    public static byte[] rsaByKey(byte[] srcData, Key Key, int mode) {
        try {
            Cipher cipher = Cipher.getInstance(CodecUtil.RSA_CHIPER);
            cipher.init(mode, Key);
            // 分段加密
            int blockSize = (mode == Cipher.ENCRYPT_MODE) ? CodecUtil.ENCRYPT_KEYSIZE : CodecUtil.DECRYPT_KEYSIZE;
            byte[] encryptedData = null;
            for (int i = 0; i < srcData.length; i += blockSize) {
                // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
                byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(srcData, i, i + blockSize));
                encryptedData = ArrayUtils.addAll(encryptedData, doFinal);
            }
            return encryptedData;

        } catch (NoSuchAlgorithmException e) {
//			//log.error("公钥算法-不存在的解密算法:", e);
        } catch (NoSuchPaddingException e) {
//			//log.error("公钥算法-无效的补位算法:", e);
        } catch (IllegalBlockSizeException e) {
//			//log.error("公钥算法-无效的块大小:", e);
        } catch (BadPaddingException e) {
//			//log.error("公钥算法-补位算法异常:", e);
        } catch (InvalidKeyException e) {
//			//log.error("公钥算法-无效的私钥:", e);
        }
        return null;
    }

    public static PrivateKey generatePrivateKey(String privateKeyStr) {
        try {
            byte[] buffer = CodecUtil.decodeHex(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey key = keyFactory.generatePrivate(keySpec);
            return key;
        } catch (Exception e) {
            return null;
        }
    }
}
