package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.dao.util.TableDaoUtils;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DiseaseServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng, Shen Lei
 * @date Created in 2021/3/24 13:13
 */
@Component
public class DiseaseTableDao extends BaseTableDao {

    public DiseaseTableDao() {
        super("Disease");
    }

    public DiseaseEntity queryDiseaseById(String id) {
        try {
            TableQuery<DiseaseServiceEntity> pkQuery = TableQuery
                    .from(DiseaseServiceEntity.class)
                    .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
            for (DiseaseServiceEntity diseaseServiceEntity : cloudTable.execute(pkQuery)) {
                return DiseaseEntity.fromServiceEntity(diseaseServiceEntity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public DiseaseEntity queryDiseaseByName(String name) {
        try {
            TableQuery<DiseaseServiceEntity> nameQuery = TableQuery
                    .from(DiseaseServiceEntity.class)
                    //.where(TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name));
                    .where(TableDaoUtils.containsPrefix("Name", name));
            for (DiseaseServiceEntity diseaseServiceEntity : cloudTable.execute(nameQuery)) {
                return DiseaseEntity.fromServiceEntity(diseaseServiceEntity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<DiseaseEntity> queryDiseases(int size) {
        try {
            List<DiseaseEntity> diseaseEntities = new ArrayList<>();
            TableQuery<DiseaseServiceEntity> rangedQuery = TableQuery
                    .from(DiseaseServiceEntity.class)
                    .take(size);
            for (DiseaseServiceEntity diseaseServiceEntity : cloudTable.execute(rangedQuery)) {
                diseaseEntities.add(DiseaseEntity.fromServiceEntity(diseaseServiceEntity));
            }
            return diseaseEntities.size() == 0 ? null : diseaseEntities;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean deleteById(String id) {
        try {
            DiseaseServiceEntity diseaseServiceEntity = new DiseaseServiceEntity(id, id);
            diseaseServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(diseaseServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(DiseaseEntity disease) {
        DiseaseServiceEntity diseaseServiceEntity = (DiseaseServiceEntity) disease.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(diseaseServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

}