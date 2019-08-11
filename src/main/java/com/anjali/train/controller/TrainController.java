package com.anjali.train.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.anjali.train.DataLoader;
import com.anjali.train.service.TrainService;
import com.anjali.train.util.Constants;
import com.anjali.train.vo.TrainVo;

@RestController
@RequestMapping(value = Constants.IRCTC_TRAIN)
public class TrainController {
	@Autowired
	private TrainService trainService;
	
	@PostMapping(value = Constants.OPERATION_CREATE)
	public Map<String, Object> create(@RequestBody TrainVo trainVo, Locale locale , Model model) {

		Map<String, Object> responseMap = trainService.createTrain(trainVo);
		return responseMap;
	}
	
	@GetMapping(value = Constants.OPERATION_READ)
	public Map<String, Object> read(@RequestBody  Map<String, Object> requestMap) {

		Integer id = new Integer(requestMap.get("id").toString());
		
		Map<String, Object> responseMap =trainService.readTrain(id);
		return responseMap;
	}
	
	@PutMapping(value = Constants.OPERATION_UPDATE)
	public Map<String, Object> update(@RequestBody TrainVo trainVo, Locale locale , Model model) {

		Map<String, Object> responseMap =trainService.updateTrain(trainVo);
		return responseMap;
	}
	
	@DeleteMapping(value = Constants.OPERATION_DELETE)
	public Map<String, Object> delete(@RequestBody  Map<String, Object> requestMap, Locale locale , Model model) {

		Integer id = new Integer(requestMap.get("id").toString());

		Map<String, Object> responseMap =trainService.deleteTrain(id);
		return responseMap;
	}
	
	@GetMapping(value = Constants.OPERATION_LIST)
	public List<TrainVo> listTrain() {

		List<TrainVo> trainList =trainService.listTrain();
		return trainList;
	}
	@GetMapping(value = Constants.OPERATION_AVAILABLE_TRAIN)
	public Map<String, Object> listAvailableTrains(@RequestParam Map<String, String> queryMap) {
		String from = queryMap.get("from"), to = queryMap.get("to");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate  date = LocalDate.parse(queryMap.get("date"),formatter);
		RestTemplate restTemplate = new RestTemplate();
		Integer fromId=DataLoader.getStationId(from);
		Integer toId=DataLoader.getStationId(to);
		if(fromId==null || toId==null)
		{
			Map<String, Object> responseMap=new  LinkedHashMap<String, Object>();
			responseMap.put(Constants.STATUS, Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE, "No Station found with this name");
			return responseMap;
		}
		Map<String, Object> trainList =trainService.listAvailableTrains(fromId, toId, date);
		return trainList;
	}

}