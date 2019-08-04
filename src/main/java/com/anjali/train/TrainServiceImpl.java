package com.anjali.train;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anjali.train.TrainDao;
import com.anjali.train.Train;
import com.anjali.train.TrainService;
import com.anjali.train.Constants;
import com.anjali.train.TrainVo;

@Component
public class TrainServiceImpl implements TrainService{
	@Autowired
	private TrainDao trainDao;
	RouteService routeService=new RouteService();

	public Map<String, Object> createTrain(TrainVo trainVo) {
Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		
		if(trainVo == null){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "Input required");
			return responseMap;
		}
		
		String name= trainVo.getTrainName();
		if (name == null || name.trim().isEmpty()){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "name can not be empty");
			return responseMap;
		}
		
		Integer capacity=trainVo.getCapacity();
		if (capacity == null || capacity <= 0 ){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "capacity can not be empty ,zero or less than zero");
			return responseMap;
		}
		
		Train train=new Train(name,capacity);
		Train savedTrain=(Train) trainDao.save(train);
		
		if (savedTrain != null) {
			responseMap.put(Constants.STATUS, Constants.STATUS_SUCCESS);
			responseMap.put(Constants.MESSAGE, "Train record created successfully");
			responseMap.put("id",savedTrain.getId());
		}
		else {
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "Something went wrong, Train cannot be create");
		}
		return responseMap;
	}

	public Map<String, Object> readTrain(Integer id) {
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		if (id == null || id.intValue() <= 0){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "id can not be empty ,zero or less than zero");
			return responseMap;
		}

		Optional<Train> trainOptional = trainDao.findById(id);

		if(trainOptional.isPresent()){
			Train train = trainOptional.get();
			TrainVo trainVo = new TrainVo(train.getId(), 
					train.getTrainName() ,
					train.getCapacity());
			responseMap.put(Constants.STATUS, Constants.STATUS_SUCCESS);
			responseMap.put(Constants.MESSAGE, "train record found");
			responseMap.put("train record: ", trainVo);
		}else {
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "no train record found");
		}
		return responseMap;
	}

	public Map<String, Object> updateTrain(TrainVo trainVo) {
Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		
		if(trainVo == null){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "Input required");
			return responseMap;
		}
		
		Integer id = trainVo.getId();
		if (id == null || id.intValue() <= 0){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "id can not be empty ,zero or less than zero");
			return responseMap;
		}
		

		Optional<Train> trainOptional = trainDao.findById(id);
		if(!trainOptional.isPresent()){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "no train record found");
			return responseMap;
		}

		Train train = trainOptional.get();
		train.setTrainName((trainVo.getTrainName() != null ? trainVo.getTrainName() : train.getTrainName()));
		train.setCapacity(trainVo.getCapacity() != null ? trainVo.getCapacity() : train.getCapacity());
		
		Train savedTrain=(Train) trainDao.save(train);
		
		if (savedTrain != null) {
			responseMap.put(Constants.STATUS, Constants.STATUS_SUCCESS);
			responseMap.put(Constants.MESSAGE, "train record updated successfully");
			responseMap.put("updated records: ",savedTrain);
		}
		else {
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "Something went wrong, train cannot be updated");
		}
		return responseMap;
	}

	public Map<String, Object> deleteTrain(Integer id) {
Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		
		if (id == null || id <= 0){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "id can not be empty ,zero or less than zero");
			return responseMap;
		}
		Optional<Train> trainOptional = trainDao.findById(id);
		if(!trainOptional.isPresent()){
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "no train record found");
			return responseMap;
		}

		Train train = trainOptional.get();
		trainDao.delete(train);
		
		responseMap.put(Constants.STATUS, Constants.STATUS_SUCCESS);
		responseMap.put(Constants.MESSAGE, "train record deleted successfully");
		return responseMap;
	}

	public List<TrainVo> listTrain() {
Iterable<Train> trainList = trainDao.findAll();
		
		List<TrainVo> trainVoList = new ArrayList<>();
		
		trainList.forEach(train -> {
			TrainVo trainVo = new TrainVo(
					train.getId(), 
					train.getTrainName(),
					train.getCapacity());
			trainVoList.add(trainVo);
		});
		
		return trainVoList;
	}

	@Override
	public List<TrainVo> listAvailableTrains(String from_station, String to_station, LocalDate date) {
		routeService.printAllPaths(2,3);
		return null;
	}

}
