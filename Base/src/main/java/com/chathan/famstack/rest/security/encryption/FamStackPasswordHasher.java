package com.chathan.famstack.rest.security.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chathan.famstack.BaseFamStackService;

public class FamStackPasswordHasher extends BaseFamStackService {
	
	private static final String MD5 = "MD5";

	public static synchronized String hashPassword(String password) {
        MessageDigest md;
        StringBuilder sb = new StringBuilder();
		try {
			md = MessageDigest.getInstance(MD5);
	        md.update(password.getBytes());
	        byte[] bytes = md.digest();
	        for(int i=0; i < bytes.length; i++) {
	            sb.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(hashPassword("password"));
	}
}
