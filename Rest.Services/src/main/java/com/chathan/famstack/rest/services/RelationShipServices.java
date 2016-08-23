package com.chathan.famstack.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.chathan.xsd.profileservices.UserProfile;

@Component
@Path("/relation")
public class RelationShipServices extends BaseRestFamStackService {
	@GET
	@Path("/createrelation")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRelation(UserProfile userProfile) {
		return Response.status(200).build();
	}
}