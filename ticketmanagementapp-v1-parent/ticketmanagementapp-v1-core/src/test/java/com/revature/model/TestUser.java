package com.revature.model;

public class TestUser {

	public static void main(String[] args) {
		User user = new User();
		user.setId(1);
		user.setName("AAA");
		user.setEmailId("aaa@gmail.com");
		user.setPassword("aaa");
		user.setActive(true);

		System.out.println(user);
	}

}
