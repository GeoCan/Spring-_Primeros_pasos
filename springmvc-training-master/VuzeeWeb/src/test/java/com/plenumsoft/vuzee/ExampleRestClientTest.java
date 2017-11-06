package com.plenumsoft.vuzee;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plenumsoft.vuzee.services.CandidateService;

@RunWith(SpringRunner.class)
@RestClientTest(CandidateService.class)
public class ExampleRestClientTest {

	@Autowired
	private CandidateService candidateService;
	
	public void getCandidate() throws Exception{
		
	}
}
