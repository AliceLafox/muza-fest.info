package net.lafox.generic;


import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public interface GenericService<T, PK extends Serializable> {

    T insert(T t);

    T update(T t);

    void delete(PK pk);

    T get(PK pk);

    List<T> getList(int start, int length, Criterion... criterionList);

    List<T> getList(int start, int length, Object... criterionAndOrderList);

    List<T> getList(Criterion... criterionList);

    Map<String, Object> getListForDataTable(Map<String, String[]> requestParamsMap, Criterion criterion, Criterion criterionSearch);

    long getCount(Criterion... criterionList);
}
