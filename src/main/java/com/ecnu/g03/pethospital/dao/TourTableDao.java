package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.TourEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TourServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng
 * @date 2021-04-08 11:51
 */
@Repository
public class TourTableDao extends BaseTableDao {

    public TourTableDao() {
        super("Tour");
    }

    public TourEntity queryTourByDepartmentId(String departmentId) {
        String filter = TableQuery.generateFilterCondition(
                "DepartmentId",
                TableQuery.QueryComparisons.EQUAL,
                departmentId);
        TableQuery<TourServiceEntity> query = TableQuery.from(TourServiceEntity.class).where(filter);

        for (TourServiceEntity tourServiceEntity : cloudTable.execute(query)) {
            return TourEntity.fromServiceEntity(tourServiceEntity);
        }
        return null;
    }

    public TourEntity queryTourById(String id) {
        String filter = TableQuery.generateFilterCondition(
                "PartitionKey",
                TableQuery.QueryComparisons.EQUAL,
                id);
        TableQuery<TourServiceEntity> query = TableQuery.from(TourServiceEntity.class).where(filter);

        for (TourServiceEntity tourServiceEntity : cloudTable.execute(query)) {
            return TourEntity.fromServiceEntity(tourServiceEntity);
        }
        return null;
    }

    public List<TourEntity> listTours(int size) {
        List<TourEntity> tourEntities = new ArrayList<>();
        TableQuery<TourServiceEntity> rangedQuery = TableQuery
                .from(TourServiceEntity.class)
                .take(size);

        for (TourServiceEntity tourServiceEntity : cloudTable.execute(rangedQuery)) {
            tourEntities.add(TourEntity.fromServiceEntity(tourServiceEntity));
        }

        return tourEntities.size() == 0 ? null : tourEntities;
    }
}
