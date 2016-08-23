package com.chathan.famstack.rest.services;
import spock.lang.Specification

import com.chathan.famstack.BaseFamStackService

class BaseFamStackServicesSpecification extends Specification{

	BaseFamStackService testObj
	
	def setup() {
		testObj = new BaseFamStackService()
	}

}
