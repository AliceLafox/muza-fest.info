package net.lafox.muza.service;

import net.lafox.generic.GenericDao;
import net.lafox.generic.GenericServiceAbstract;
import net.lafox.muza.entity.Item;
import net.lafox.muza.validator.ItemForm;
import net.lafox.usr.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ItemServiceImpl extends GenericServiceAbstract<Item, Long> implements ItemService {

    @Qualifier("categoryServiceImpl")
    @Autowired
    private CategoryService categoryService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;


    @Autowired
    public void setGenericDao(GenericDao<Item, Long> genericDao) {
        super.setGenericDao(genericDao);
    }


    @Override
    public void saveFormData(ItemForm itemForm) throws Exception {
        Item item = get(itemForm.getId());
        item.setTitle(itemForm.getTitle());
        item.setContent(itemForm.getContent());
        item.setCategory(categoryService.get(itemForm.getCategory()));
        item.setEnabled(itemForm.isEnabled());
        item.setDateStart(itemForm.getDateStart());
        if (isValidUser(item)) {//item belongs to user
            update(item);
        } else {
            throw new Exception("Invalid User");
        }
        //todo check user roles, after that item belongs to user
    }
    @Override
    public boolean isValidUser(Item item){
        return Objects.equals(userService.getCurrentUser().getUsername(), item.getUser().getUsername());
    }
}
