package com.ecnu.g03.pethospital.controller.admin;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Post;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.paper.TestPaperNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.paper.*;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.service.TestPaperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 15:27
 */
@RestController
@RequestMapping(value = "/admin/paper", produces = "application/json; charset=UTF-8")
public class TestPaperControllerM {

    private final TestPaperService testPaperService;

    TestPaperControllerM(TestPaperService testPaperService) {
        this.testPaperService = testPaperService;
    }

    @GetMapping("/all")
    public TestPaperGetAllResponse getAll() {
        TestPaperGetAllResponse response = new TestPaperGetAllResponse();
        List<TestPaperEntity> testPaperEntityList = testPaperService.queryAll();
        if (testPaperEntityList.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setTestPapers(testPaperEntityList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/delete/{id}")
    public TestPaperDeleteResponse deleteById(@PathVariable("id") String id) {
        TestPaperDeleteResponse response = new TestPaperDeleteResponse();
        if (!testPaperService.deleteById(id)) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setSuccessful(true);
        return response;
    }

    @PostMapping("/new")
    public TestPaperNewResponse insert(@RequestBody TestPaperNewRequest request) {
        TestPaperNewResponse response = new TestPaperNewResponse();
        TestPaperEntity testPaperEntity = testPaperService.insert(request.getQuestions());
        if (testPaperEntity == null){
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setTestPaper(testPaperEntity);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/search/{id}")
    public TestPaperSearchResponse searchByIdAndName(@PathVariable("id") String id) {
        TestPaperSearchResponse response = new TestPaperSearchResponse();
        List<TestPaperEntity> testPapers = testPaperService.searchById(id);
        if (testPapers.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setTestPapers(testPapers);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/detail/{id}")
    public TestPaperDetailResponse searchById(@PathVariable("id") String id) {
        TestPaperDetailResponse response = new TestPaperDetailResponse();
        List<TestPaperEntity> testPapers = testPaperService.searchById(id);
        if (testPapers.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setTestPaper(testPapers.get(0));
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
