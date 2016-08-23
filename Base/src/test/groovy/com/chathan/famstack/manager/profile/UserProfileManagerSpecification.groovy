package com.chathan.famstack.manager.profile;
import spock.lang.Specification;

class UserProfileManagerSpecification extends Specification{

	UserProfileManager testObj
	
	def setup() {
		testObj = new UserProfileManager()
	}

	def "testing"() {
		when:
		def result = testObj.registerUser() 
		
		then:
		result == "user registered"
		
	}
}
