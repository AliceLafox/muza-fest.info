package net.lafox.muza.dao;

import net.lafox.generic.GenericDaoAbstract;
import net.lafox.muza.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends GenericDaoAbstract<Category,String> implements CategoryDao  {}
