package net.lafox.muza.dao;

import net.lafox.generic.GenericDaoAbstract;
import net.lafox.muza.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends GenericDaoAbstract<Item,Long> implements ItemDao  {}
