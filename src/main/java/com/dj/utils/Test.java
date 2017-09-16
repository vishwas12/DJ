package com.dj.utils;

public class Test {

	public static void main(String[] args) {
		String password ="password";
		String email = "ahuja.sagar14@gmail.com";
		String pwd = EncryptionUtils.passwordEncoder(email, password);
		System.out.println(pwd);
		System.out.println(pwd.equals("u2ehLVRKHqqIkaOfNpgRrkiQNvEw7oDcovx0mlYfPL7CeejUOXkY6jJStJ/yKnZTl8zsSVaUVGOOdQ56XQ58RA=="));
	}

}
