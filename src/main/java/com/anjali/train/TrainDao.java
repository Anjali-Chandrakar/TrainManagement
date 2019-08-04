package com.anjali.train;
import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.anjali.train.Train;

@Repository
public interface TrainDao<T extends Train,Integer extends Serializable> extends CrudRepository<T, Integer> {

}
