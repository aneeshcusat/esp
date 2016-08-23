package com.chathan.famstack.rest.services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.chathan.xsd.profileservices.UserProfile;
import com.famstack.esp.manager.profile.UserProfileManager;
import com.famstack.esp.rest.security.FamStackAuthenticationToken;
import com.famstack.esp.rest.security.encryption.FamStackPasswordHasher;
import com.famstack.esp.rest.security.login.UserSecurityContextBinder;

@Component
@Path("/user")
public class UserProfileManagementServices extends BaseRestFamStackService {

	@Resource
	private UserSecurityContextBinder userSecurityContextBinder;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	UserProfileManager userProfileManager;

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(UserProfile userProfile) {

		String result = userProfileManager.registerUser();

		return Response.status(200).entity(result).build();

	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(UserProfile userProfile) {
		String hashPassword = FamStackPasswordHasher.hashPassword(userProfile.getPassword());
		FamStackAuthenticationToken token = new FamStackAuthenticationToken(userProfile.getLoginName(), hashPassword);
		Authentication authentication = authenticationManager.authenticate(token);
		if (authentication.isAuthenticated()) {
			userSecurityContextBinder.bindUserAuthentication(authentication);
			return Response.status(200).build();
		}
		return Response.status(403).build();
	}

	@GET
	@Path("/getuser")
	@Produces(MediaType.APPLICATION_JSON)
	public UserProfile getUserDetails(@Context HttpServletRequest request) {
		return userProfileManager.getUserDetails();
	}

	public void logOut() {
		userSecurityContextBinder.unbindUserAuthentication();
	}

}
