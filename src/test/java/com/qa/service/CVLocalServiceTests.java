package com.qa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.qa.domain.CV;
import com.qa.domain.Trainee;
import com.qa.testConstants.Constants;




@RunWith(MockitoJUnitRunner.class)
public class CVLocalServiceTests {
	
	@InjectMocks
	private CVServiceLocal service;
	


	@Test
	public void testGetAllTrainees() {
		List<Trainee> traineeList = new ArrayList<Trainee>();
		Trainee trainee = new Trainee();
		traineeList.add(trainee);
		Assert.assertEquals(traineeList, service.getAllTrainees());
		
	}
	
	@Test 
	public void testPutFileIntoCVObject() {
		MockMultipartFile mockMultipartFile = new MockMultipartFile(Constants.MOCK_TRAINEE_FILE,Constants.MOCK_FILENAME,
	              Constants.FILE_TYPE, Constants.MOCK_TEST_DATA.getBytes());
		CV cv = new CV();
		cv.setFiles(mockMultipartFile);
		Assert.assertEquals(cv, service.putFileIntoCVObject(mockMultipartFile));
		
	}
	
	@Test
	public void testUpdateCVList() {
		CV cv = new CV();
		Trainee trainee = new Trainee();
		List<Trainee> traineeList= new ArrayList<Trainee>();
		trainee.setID(1L);
		trainee.setCvList(null);
		traineeList.add(trainee);
		List<Optional<CV>> cvList = new ArrayList<Optional<CV>>();
		cvList.add(Optional.of(cv));
		Assert.assertEquals(trainee.getCvList(), service.updateCVList(Optional.of(cv), trainee.getID()));
	}
	
	@Test
	public void testCreateTrainee() {
		Trainee trainee = new Trainee();
		Assert.assertEquals(trainee, service.createTrainee(trainee));
	}
	
	@Test
	public void testTraineeWithID() {
		Trainee trainee = new Trainee();
		List<Trainee> traineeList= new ArrayList<Trainee>();
		trainee.setFirstName("Toby");
		trainee.setLastName("Toby");
		trainee.setCurrentlyHired(true);
		trainee.setFlagged(true);
		trainee.setUserName("Toby");
		trainee.setID(1L);
		trainee.setCvList(null);
		traineeList.add(trainee);
		Mockito.when(service.findTraineeByID(trainee.getID())).thenReturn(trainee);
		Assert.assertEquals(trainee.getFirstName(), service.traineeWithID(1L).getFirstName());
		Assert.assertEquals(trainee.getLastName(), service.traineeWithID(1L).getLastName());
		Assert.assertEquals(trainee.getUserName(), service.traineeWithID(1L).getUserName());
		Assert.assertEquals(trainee.isCurrentlyHired(), service.traineeWithID(1L).isCurrentlyHired());
		Assert.assertEquals(trainee.isFlagged(), service.traineeWithID(1L).isFlagged());
		Assert.assertEquals(trainee.getID(), service.traineeWithID(1L).getID());	
		
	}
	
	@Test
	public void testFindTraineeByID() {
		Trainee trainee = new Trainee();
		trainee.setID(1L);
		List<Trainee> traineeList = new ArrayList<Trainee>();
		traineeList.add(trainee);
		Assert.assertEquals(trainee, service.findTraineeByID(trainee.getID()));
		
		
	}
	
	
	
	
	

}
