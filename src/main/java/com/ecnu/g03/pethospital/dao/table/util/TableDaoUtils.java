package com.ecnu.g03.pethospital.dao.table.util;

import com.microsoft.azure.storage.table.TableQuery;

/**
 * @author Juntao Peng
 * @date 2021/3
 */
public class TableDaoUtils {
    public static String containsPrefix(String key, String prefix) {
        char lastCharAddByOne = (char) (prefix.charAt(prefix.length()-1)+1);
        String prefixUpperBound = prefix.substring(0, prefix.length() - 1) + lastCharAddByOne;
        return TableQuery.combineFilters(
                TableQuery.generateFilterCondition(key, TableQuery.QueryComparisons.GREATER_THAN_OR_EQUAL, prefix),
                TableQuery.Operators.AND,
                TableQuery.generateFilterCondition(key, TableQuery.QueryComparisons.LESS_THAN, prefixUpperBound)
        );
    }
}
