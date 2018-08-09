package com.heitian.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import com.heitian.ssm.model.Car;
import com.heitian.ssm.model.Add;
import com.heitian.ssm.service.CarService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/car")

public class CarController {

    private Logger log = Logger.getLogger(CarController.class);
    @Resource
    private CarService carService;

    @RequestMapping("/showCar")
    public String showCar(HttpServletRequest request, Model model){
        log.info("查询车辆信息");
        String urlVariables =carService.getCookie();
//        String urlVariables =carService.doPostJson();
//        String urlVariables =carService.message2();
//        List<Car> carList = carService.getAllInfo();
//        List<Add> carList = carService.addUser();
//        model.addAttribute("carList",carList);
        model.addAttribute("carList",urlVariables);
//        log.info(carList);
        return "showCar";

    }



}
