package com.anjali.train;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.anjali.train.dao.TrainDao;
import com.anjali.train.model.Train;
import com.anjali.train.service.TrainServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainManagementApplicationTests {

	@Autowired
	TrainServiceImpl mockService;
	
	@MockBean
	TrainDao mockDao;
	
	@Test
	public void testListTrain() {
		when(mockDao.findAll()).thenReturn((Iterable) Stream.of(new Train(1,"testTrain1",30,"1, 3, 4, 5"),new Train(2,"testTrain2",30,"1, 2, 4")));
		assertEquals(2,mockService.listTrain().size());
		
	}

}
