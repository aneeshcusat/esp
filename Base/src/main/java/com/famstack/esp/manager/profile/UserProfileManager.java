package com.famstack.esp.manager.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.chathan.xsd.profileservices.ObjectFactory;
import com.chathan.xsd.profileservices.UserProfile;
import com.famstack.esp.BaseFamStackService;
import com.famstack.esp.dataaccess.entity.UserEntity;
import com.famstack.esp.dataaccess.entity.manager.FamStackEntityManager;
import com.famstack.esp.rest.security.login.LoginResult;

public class UserProfileManager extends BaseFamStackService {

	@Autowired
	ObjectFactory objectFactory;

	@Autowired
	FamStackEntityManager<UserEntity> famStackEntityManager;

	public String registerUser() {
		return "user registered";
	}

	public LoginResult login(String userName, String hashedPassword) {
		Map<String, String> queryParameter = new HashMap<String, String>();
		queryParameter.put("userName", userName);
		queryParameter.put("password", hashedPassword);
		UserEntity userEntity = famStackEntityManager.queyItem(queryParameter, UserEntity.class);
		if (userEntity != null && userEntity.getUserName().equals(userName)) {
			return LoginResult.SUCCESS;
		}
		return LoginResult.FAILED;
	}

	public UserProfile getUserDetails() {

		logDebug("Executing user profilermanager");
		List<UserEntity> userEntity = famStackEntityManager.getAllItems(UserEntity.class);
		UserProfile userProfile = objectFactory.createUserProfile();
		userProfile.setFirstName(userEntity.get(0).getFirstName());
		userProfile.setLastName(userEntity.get(0).getLastName());
		return userProfile;
	}

}
