package com.revature.util;

public class TestMailUtil {

	public static void main(String[] args) {
		try {
			MailUtil.sendSimpleMail(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
