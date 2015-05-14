package net.lafox.muza.service;

import net.lafox.generic.GenericService;
import net.lafox.muza.entity.Item;
import net.lafox.muza.validator.ItemForm;

@SuppressWarnings("unused")
public interface ItemService extends GenericService<Item, Long> {

    void saveFormData(ItemForm itemForm) throws Exception;

    boolean isValidUser(Item item);
}
