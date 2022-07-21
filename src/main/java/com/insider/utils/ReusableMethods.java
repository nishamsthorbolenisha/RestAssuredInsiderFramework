package com.insider.utils;

public class ReusableMethods {

	
	/**
	 * This method generate Random String
	 * @param n
	 * @return
	 */
	public static String getRandomString(int n) {

		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {

			int index = (int) (str.length() * Math.random());

			sb.append(str.charAt(index));
		}

		return sb.toString();
	}

	/**
	 * This method generate Random Integer
	 * @param n
	 * @return
	 */
	public static int getRandomInteger(int n) {

		String str = "1234567890";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {

			int index = (int) (str.length() * Math.random());

			sb.append(str.charAt(index));
		}

		return Integer.parseInt(sb.toString());
	}
}
