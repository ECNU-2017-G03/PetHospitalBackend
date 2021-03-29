package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import com.ecnu.g03.pethospital.model.serviceentity.ToolServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Jiayi Zhu
 * @date 2021-03-29 21:51
 */
@Repository
public class ToolTableDao extends BaseTableDao {

    public ToolTableDao() {
        super("Tool");
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
}
