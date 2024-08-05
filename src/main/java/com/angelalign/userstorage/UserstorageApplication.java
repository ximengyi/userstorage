package com.angelalign.userstorage;

import com.angelalign.userstorage.user.MyUserStorageProviderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserstorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyUserStorageProviderFactory.class, args);
	}

}
