package net.lafox.generic;

import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class GenericServiceAbstract<T, PK extends Serializable> implements GenericService<T, PK> {

    @SuppressWarnings("unused")
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private GenericDao<T, PK> genericDao;

    public void setGenericDao(GenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public T insert(T t) {
        return genericDao.insert(t);
    }

    @Override
    public T update(T t) {
        return genericDao.update(t);
    }

    @Override
    public void delete(PK pk) {
        genericDao.delete(pk);
    }

    @Override
    public T get(PK pk) {
        return genericDao.get(pk);
    }

    @Override
    public List<T> getList(int start, int length, Criterion... criterionList){
        return genericDao.getList(start, length, criterionList);
    }

    @Override
    public List<T> getList(int start, int length, Object... criterionAndOrderList){
        return genericDao.getList(start, length, criterionAndOrderList);
    }

    @Override
    public List<T> getList(Criterion... criterionList) {
        return genericDao.getList(criterionList);
    }

    @Override
    public Map<String,Object> getListForDataTable(Map<String, String[]> requestParamsMap, Criterion criterion, Criterion criterionSearch){
        return genericDao.getListForDataTable(requestParamsMap, criterion, criterionSearch);
    }

    @Override
    public long getCount(Criterion... criterionList) {
        return genericDao.getCount(criterionList);
    }
}
