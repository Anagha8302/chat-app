package com.elitesoftwares.chatapp.utils;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Encryption {
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException{
		String encryptedPassword=null;
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		messageDigest.update(plainPassword.getBytes());
		byte []encrypt=messageDigest.digest();
		StringBuffer sb=new StringBuffer();
		for(byte b:encrypt) {
			sb.append(b);
		}
		encryptedPassword=sb.toString();
		//System.out.println("Encrypted Password "+encryptedPassword);
		return encryptedPassword;
	}

public static void main(String[]args) {
	
}
}
