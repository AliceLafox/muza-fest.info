package net.lafox.generic;

import net.lafox.util.DataTableUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDaoAbstract<T, PK extends Serializable> implements GenericDao<T, PK> {

    @SuppressWarnings("unused")
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    private Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public T insert(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
        return t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(PK pk) {
        Session session = sessionFactory.getCurrentSession();
        T t = (T) session.load(persistentClass, pk);
        session.delete(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(PK pk) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(persistentClass, pk);
    }

    @Override
    public Map<String, Object> getListForDataTable(Map<String, String[]> requestParamsMap, Criterion criterion, Criterion criterionSearch) {
        Map<String, Object> map = new HashMap<>();
        if (requestParamsMap.containsKey("draw")) map.put("draw", requestParamsMap.get("draw")[0]);
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(persistentClass);
        if (requestParamsMap.containsKey("start")) criteria.setFirstResult(Integer.parseInt(requestParamsMap.get("start")[0]));
        if (requestParamsMap.containsKey("length")) criteria.setMaxResults(Integer.parseInt(requestParamsMap.get("length")[0]));
        if (criterion != null) criteria.add(criterion);
        if (criterionSearch != null) criteria.add(criterionSearch);

        for (Order order : DataTableUtils.getOrderList(requestParamsMap)) criteria.addOrder(order);

        map.put("data", criteria.list());
        map.put("recordsTotal", getCount(criterion));
        map.put("recordsFiltered", getCount(criterion, criterionSearch));
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList(int start, int length, Criterion... criterionList) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(persistentClass);
        for (Criterion criterion : criterionList) {
            if (criterion != null) criteria.add(criterion);
        }
        if (start > 0) criteria.setFirstResult(start);
        if (length > 0) criteria.setMaxResults(length);

        return (List<T>) criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList(int start, int length, Object... criterionAndOrderList) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(persistentClass);
        for (Object o : criterionAndOrderList) {
            if (o != null && o instanceof Criterion) criteria.add((Criterion) o);
            if (o != null && o instanceof Order) criteria.addOrder((Order) o);
        }
        if (start > 0) criteria.setFirstResult(start);
        if (length > 0) criteria.setMaxResults(length);

        return (List<T>) criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList(Criterion... criterionList) {
        return getList(0, 0, criterionList);
    }

    @Override
    public long getCount(Criterion... criterionList) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(persistentClass);
        for (Criterion criterion : criterionList) {
            if (criterion != null) criteria.add(criterion);
        }
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
