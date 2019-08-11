package com.anjali.train.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.anjali.train.vo.TrainVo;


public interface TrainService {

	Map<String, Object> createTrain(TrainVo trainVo);
	Map<String, Object> readTrain(Integer id);
	Map<String, Object> updateTrain(TrainVo trainVo);
	Map<String, Object> deleteTrain(Integer id);
	List<TrainVo> listTrain();
	Map<String, Object> listAvailableTrains(Integer from_satation,Integer to_station, LocalDate date);
}