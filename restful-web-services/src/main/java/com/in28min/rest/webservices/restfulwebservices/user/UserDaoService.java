package com.in28min.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int ucount = 3;

	private UserDaoService() {
	}

	List<User> getUsers() {

		users.add(new User(1, "Adem", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
		return users;
	}

	public List<User> findAll() {
		return users;
	}

	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++ucount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

	public User deleteUser(int id) {
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getId() == id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}

}
