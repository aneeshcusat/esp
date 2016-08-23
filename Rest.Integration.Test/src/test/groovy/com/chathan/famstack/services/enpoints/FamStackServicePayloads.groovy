package com.chathan.famstack.services.enpoints

class FamStackServicePayloads {
	
 static def getUserLoginRequestPayload (username, password) {
	 
	 return '''
	 		{
	 			"username":"''' + username +'''",
	 			"password":"''' + password +'''"
	 		}'''
 }
 
}
