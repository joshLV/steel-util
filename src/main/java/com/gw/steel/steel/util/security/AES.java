package com.gw.steel.steel.util.security;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;



/**
 * AES 加密算法
 * @author Administrator
 *
 */

public class AES {
	
	public static final int keyLength = 16; // 加密key与向量长度为16位

	private static final String CHARSET = "UTF-8";
	
	private static final String AESTYPE ="AES/CBC/NoPadding";
	
	public static String encrypt(String content, String key)
			throws Exception {
		if (content == null || key == null) {
			throw new IllegalArgumentException("key or content cannot be null");
		}
		if (key.length() > keyLength) {
			throw new IllegalArgumentException("key length must be between 1 and 16");
		} else {
			key = rightPad(key);
		}
		
		byte[] dataBytes = content.getBytes(CHARSET);

		SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(CHARSET), "AES");

		Cipher cipher = Cipher.getInstance(AESTYPE);// "算法/模式/补码方式"
		int plaintextLength = dataBytes.length;
		int blockSize = cipher.getBlockSize();
		if (plaintextLength % blockSize != 0) {
			plaintextLength = plaintextLength
					+ (blockSize - (plaintextLength % blockSize));
		}

		byte[] plaintext = new byte[plaintextLength];
		System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

		IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET));// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, keyspec, iv);
		byte[] encrypted = cipher.doFinal(plaintext);

		return Base64.encodeBase64String(encrypted);// 使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	public static String decrypt(String content, String key)
			throws Exception {
		if (content == null || key == null) {
			throw new IllegalArgumentException("key or content cannot be null");
		}
		
		if (key.length() > keyLength) {
			throw new IllegalArgumentException(
					"key length must be between 1 and 16");
		} else {
			key = rightPad(key);
		}

		byte[] raw = key.getBytes(CHARSET);
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance(AESTYPE);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET));
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] encrypted1 = Base64.decodeBase64(content);// 先用base64解密
		try {
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, CHARSET);
			return originalString.trim();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对字符串不满足指定长度的在右侧补空格
	 * 
	 * @param targetStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String rightPad(String key)
			throws UnsupportedEncodingException {
		
		StringBuilder sb = new StringBuilder(keyLength);
		sb.append(key);
		
		for (int i = 0; i < keyLength - key.length(); i++) {
			sb.append(' ');
		}
		return sb.toString();
	}


	/**
	 * 从普通字符串转换为适用于URL的Base64编码字符串
	 * 
	 * @param normalString
	 * @return
	 */
	public static String base64Replace(String normalString) {
		return normalString.replace('+', '*').replace('/', '-')
				.replace('=', '.');
	}

	/**
	 * 从普通字符串转换为适用于URL的Base64编码字符串
	 * 
	 * @param base64String
	 * @return
	 */
	public static String base64Restore(String base64String) {
		return base64String.replace('.', '=').replace('*', '+')
				.replace('-', '/');
	}

	public static void main(String[] args) throws Exception {
		
		String key = "jf.gw.com.cn";
		String content = "hello word";
		
		String encrypt = encrypt(content, key);
		
		System.out.println(encrypt);
		
		System.out.println(decrypt(encrypt, key));
	}
}