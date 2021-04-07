package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import com.ecnu.g03.pethospital.model.serviceentity.ToolServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu, Xueying Li
 * @date 2021-03-29 21:51
 */
@Repository
public class ToolTableDao extends BaseTableDao {

    public ToolTableDao() {
        super("Tool");
    }

    public boolean insert(ToolEntity toolEntity) {
        ToolServiceEntity toolServiceEntity = (ToolServiceEntity) toolEntity.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(toolServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(String id) {
        try {
            ToolServiceEntity toolServiceEntity = new ToolServiceEntity(id, id);
            toolServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(toolServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ToolEntity queryToolById(String id) {
        String filter = TableQuery.generateFilterCondition(
                "PartitionKey",
                TableQuery.QueryComparisons.EQUAL,
                id);
        TableQuery<ToolServiceEntity> query = TableQuery.from(ToolServiceEntity.class).where(filter);

        for (ToolServiceEntity toolServiceEntity : cloudTable.execute(query)) {
            return ToolEntity.fromServiceEntity(toolServiceEntity);
        }
        return null;
    }

    public List<ToolEntity> queryAll(){
        List<ToolEntity> result = new ArrayList<>();
        TableQuery<ToolServiceEntity> query = TableQuery
                .from(ToolServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(ToolEntity.fromServiceEntity(s))
        );
        return result;
    }

    public ToolEntity update(ToolEntity toolEntity) {
        try {
            ToolServiceEntity toolServiceEntity = (ToolServiceEntity) toolEntity.toServiceEntity();
            toolServiceEntity.setEtag("*");
            TableOperation operation = TableOperation.merge(toolServiceEntity);
            cloudTable.execute(operation);
            return toolEntity;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
