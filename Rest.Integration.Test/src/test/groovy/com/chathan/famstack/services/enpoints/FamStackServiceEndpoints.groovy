package com.chathan.famstack.services.enpoints

import com.chathan.famstack.services.constants.Endpoints;

class FamStackServiceEndpoints extends Endpoints {
	
	static def loginUser() {
		return"${baseUrl()}/rest/user/login"
	}
}
