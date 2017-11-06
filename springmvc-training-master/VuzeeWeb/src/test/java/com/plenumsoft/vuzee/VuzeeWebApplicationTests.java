package com.plenumsoft.vuzee;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.services.CandidateService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VuzeeWebApplicationTests {

	@Autowired
	CandidateService service;
	
	@Test
	public void insertTest() {
		Candidate candidato = new Candidate();
		candidato.setName("Andres");
		candidato.setCreatedAt(new Date());
		candidato.setCreatedBy("GeoCan");
		candidato.setPositionApplied("JR10");
		
		try {
			service.addCandidate(candidato);
			Assert.assertNotNull("Error al insertar", candidato.getId());
			Assert.assertTrue(true);
		} catch (Exception ex) {
			// TODO: handle exception
			Assert.assertFalse("Error al insertar", false);
		}
		
	}

}
