package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.dto.enduser.response.department.PanoramaResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.department.TourResponse;
import com.ecnu.g03.pethospital.service.TourService;
import com.ecnu.g03.pethospital.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juntao Peng
 * @date Created in 2021/4/8 11:24
 */
@RestController
@RequestMapping("/user/tour")
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping(value = "/queryPanorama", params = {"departmentId"})
    @JwtToken
    public ResponseEntity<?> queryPanorama(@RequestParam("departmentId") String departmentId) {
        PanoramaResponse response = tourService.queryPanoramaByDepartmentId(departmentId);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/listTours")
    @JwtToken
    public ResponseEntity<?> listTours() {
        TourResponse response = tourService.listTours(20);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
