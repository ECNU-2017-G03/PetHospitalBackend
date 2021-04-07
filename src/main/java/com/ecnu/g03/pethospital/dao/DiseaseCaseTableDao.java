package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.dao.util.TableDaoUtils;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DiseaseCaseServiceEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/24 13:13
 */
@Component
public class DiseaseCaseTableDao extends BaseTableDao {

    public DiseaseCaseTableDao() {
        super("DiseaseCase");
    }

    public DiseaseCaseEntity queryDiseaseCaseById(String id) {
        try {
            TableQuery<DiseaseCaseServiceEntity> pkQuery = TableQuery
                    .from(DiseaseCaseServiceEntity.class)
                    .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
            for (DiseaseCaseServiceEntity diseaseCaseServiceEntity : cloudTable.execute(pkQuery)) {
                return DiseaseCaseEntity.fromServiceEntity(diseaseCaseServiceEntity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public DiseaseCaseEntity queryDiseaseCaseByName(String name) {
        try {
            TableQuery<DiseaseCaseServiceEntity> nameQuery = TableQuery
                    .from(DiseaseCaseServiceEntity.class)
                    //.where(TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name));
                    .where(TableDaoUtils.containsPrefix("Name", name));
            for (DiseaseCaseServiceEntity diseaseCaseServiceEntity : cloudTable.execute(nameQuery)) {
                return DiseaseCaseEntity.fromServiceEntity(diseaseCaseServiceEntity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<DiseaseCaseEntity> queryDiseaseCases(int size) {
        try {
            List<DiseaseCaseEntity> diseaseCaseEntities = new ArrayList<>();
            TableQuery<DiseaseCaseServiceEntity> rangedQuery = TableQuery
                    .from(DiseaseCaseServiceEntity.class)
                    .take(size);
            for (DiseaseCaseServiceEntity diseaseCaseServiceEntity : cloudTable.execute(rangedQuery)) {
                diseaseCaseEntities.add(DiseaseCaseEntity.fromServiceEntity(diseaseCaseServiceEntity));
            }
            return diseaseCaseEntities.size() == 0 ? null : diseaseCaseEntities;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(String id) {
        try {
            DiseaseCaseServiceEntity diseaseCaseServiceEntity = new DiseaseCaseServiceEntity(id, id);
            diseaseCaseServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(diseaseCaseServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //public DiseaseCaseEntity insert(String desc, String disease, String name, String petInfo, String picture, String video) {
//
    //}

}