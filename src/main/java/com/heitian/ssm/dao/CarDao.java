package com.heitian.ssm.dao;

import com.heitian.ssm.model.Car;
import com.heitian.ssm.model.Add;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarDao {

    List<Car> selectAllInfo();
    List<Add> addUser();
}
