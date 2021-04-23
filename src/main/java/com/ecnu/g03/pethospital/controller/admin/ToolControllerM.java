package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.tool.ToolNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.tool.ToolUpdateRequest;
import com.ecnu.g03.pethospital.dto.admin.response.tool.*;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import com.ecnu.g03.pethospital.service.AdminService;
import com.ecnu.g03.pethospital.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/7 13:33
 */
@RestController
@RequestMapping(value = "/admin/tool", produces = "application/json; charset=UTF-8")
public class ToolControllerM {

    private final ToolService toolService;

    @Autowired
    public ToolControllerM(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping("/all")
    public ToolGetAllResponse getAll(){
        ToolGetAllResponse response = new ToolGetAllResponse();
        List<ToolEntity> toolEntities = toolService.getAll();
        if (toolEntities.size() > 0) {
            response.setMessage("Request successful");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setTools(toolEntities);
        } else {
            response.setMessage("No tool found");
            response.setStatus(ResponseStatus.NO_DATA);
            response.setTools(null);
        }
        return response;
    }

    /**
     * @param request name, description, picture(base64)
     * @return {@link ToolNewResponse}
     */
    @PostMapping("/new")
    public ToolNewResponse insertTool(@RequestBody ToolNewRequest request) {
        ToolNewResponse response = new ToolNewResponse();
        ToolEntity entity = toolService.insert(request.getName(), request.getDescription(), request.getPicture());
        if (entity == null) {
            response.setMessage("Cannot add new tool");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("Insert tool successfully");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setTool(entity);
        return response;
    }

    @GetMapping("/delete/{id}")
    public ToolDeleteResponse delete(@PathVariable("id") String id) {
        ToolDeleteResponse response = new ToolDeleteResponse();
        if (!toolService.deleteById(id)) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            response.setSuccessful(false);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setSuccessful(true);
        return response;
    }

    @GetMapping("/search/{id}")
    public ToolSearchResponse searchByIdAndName(@PathVariable("id") String id) {
        ToolSearchResponse response = new ToolSearchResponse();
        List<ToolEntity> tools = toolService.searchById(id);
        if (tools.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setTools(tools);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/detail/{id}")
    public ToolDetailResponse searchById(@PathVariable("id") String id) {
        ToolDetailResponse response = new ToolDetailResponse();
        List<ToolEntity> tools = toolService.searchById(id);
        if (tools.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setTool(tools.get(0));
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping("/update")
    public ToolUpdateResponse update(@RequestBody ToolUpdateRequest request) {
        ToolUpdateResponse response = new ToolUpdateResponse();
        ToolEntity toolEntity = toolService.update(request.getId(), request.getName(), request.getDescription(), request.getPicture());
        if (toolEntity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setTool(toolEntity);
        return response;
    }

}
