package net.lafox.muza.dao;

import net.lafox.generic.GenericDaoAbstract;
import net.lafox.muza.entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends GenericDaoAbstract<Image,Long> implements ImageDao  {}
