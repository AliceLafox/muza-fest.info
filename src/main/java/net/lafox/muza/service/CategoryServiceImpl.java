package net.lafox.muza.service;

import net.lafox.generic.GenericDao;
import net.lafox.generic.GenericServiceAbstract;
import net.lafox.muza.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceAbstract<Category,String> implements CategoryService{
    @Autowired
    public void setGenericDao(GenericDao<Category,String> genericDao) {
        super.setGenericDao(genericDao);
    }
}
