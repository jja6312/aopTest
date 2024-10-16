package com.ssafy.debug.util;

import com.ssafy.debug.model.dto.Student;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Aspect
@Component
public class OpenCrypt {

    public OpenCrypt() {
        System.out.println("OpenCrypt Aspect가 빈으로 등록되었습니다.");

    }

    @Pointcut("execution(* com.ssafy.debug.model.service.*.regist*(..))")
    public void myPointCut(){
    }

    @Pointcut("execution(* com.ssafy.debug.model.service.*.get*(..))")
    public void myPointCut2(){
    }

    @Around("myPointCut()")
    public Object encryptPw(ProceedingJoinPoint pjt){
        System.out.println("aop의 encryptPw호출됨");
        Object[] args = pjt.getArgs();
        Student student = (Student) args[0];
        String source = student.getPhoneNumber();
        String salt = "salt";//이런 솔트는 아주 위험하다. 원래는 랜덤값으로 주어진다.

        byte[] hashPw = getSHA256(source, salt);// 암호화
        String hashPw2 = byteArrayToHex(hashPw);// 암호화2 ?
        student.setPhoneNumber(hashPw2);

        try {
            return pjt.proceed();
        }catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] getSHA256(String source, String salt) {
        byte byteData[] = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes());
            md.update(salt.getBytes());
            byteData = md.digest();
            System.out.println("원문: " + source + "   SHA-256: " + byteData.length + "," + byteArrayToHex(byteData));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteData;
    }

//    @Around("myPointCut2()")
    public static byte[] generateKey(String algorithm, int keySize) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public static String aesEncrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = cipher.doFinal(msg.getBytes());
        return byteArrayToHex(encrypted);

    }

    public static String aesDecrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = hexToByteArray(msg);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    // byte[] to hex
    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }


}
