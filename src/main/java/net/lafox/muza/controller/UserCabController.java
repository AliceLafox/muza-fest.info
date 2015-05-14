package net.lafox.muza.controller;

import net.lafox.muza.entity.Item;
import net.lafox.muza.service.CategoryService;
import net.lafox.muza.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("usercab")
public class UserCabController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserCabController.class);

    @Qualifier("itemServiceImpl")
    @Autowired
    private ItemService itemService;

    @Qualifier("categoryServiceImpl")
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String userCabIndexPage(Model model) {
        model.addAttribute("categoryList", categoryService.getList());
        return "usercab/index";
    }

    @RequestMapping(value = {"/item"}, method = RequestMethod.GET)
    public String userCabItemPage(Model model) {
        model.addAttribute("categoryList", categoryService.getList());
        return "usercab/itemList";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String userCabItemEdit(ModelMap map, @PathVariable long id) {
        Item item = itemService.get(id);
        if (!itemService.isValidUser(item)) {//item belongs to user
            return "error/403";
        }

        map.put("itemForm", item);
        map.put("categoryList", categoryService.getList());
        return "usercab/itemEdit";
    }


}
