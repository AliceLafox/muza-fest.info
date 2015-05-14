package net.lafox.muza.controller;

import info.bliki.wiki.model.WikiModel;
import net.lafox.muza.entity.Item;
import net.lafox.muza.service.CategoryService;
import net.lafox.muza.service.ItemService;
import org.hibernate.criterion.Restrictions;
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
public class RootController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @Qualifier("categoryServiceImpl")
    @Autowired
    private CategoryService categoryService;

    @Qualifier("itemServiceImpl")
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("categoryList", categoryService.getList());
        model.addAttribute("items", itemService.getList(0, 3, Restrictions.eq("enabled", true)));
        return "index";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String itemView(ModelMap map, @PathVariable long id) {
        Item item = itemService.get(id);
        if (!item.isEnabled()) {
            return "error/403";
        }
        map.put("item", item);
        map.put("categoryList", categoryService.getList());
        map.put("contentCompiled", WikiModel.toHtml(item.getContent()));
        return "itemView";
    }

    @RequestMapping(value = "{category:music|movie|photoart|painting|theater|poetry|prose|news|festival|author}", method = RequestMethod.GET)
    public String categoryIndex(ModelMap map, @PathVariable String category) {
        map.put("categoryList", categoryService.getList());
        map.put("categoryTitle", categoryService.get(category).getTitle());
        map.put("items", itemService.getList(0, 3, Restrictions.eq("enabled", true), Restrictions.eq("category", categoryService.get(category))));
        return "categoryIndex";
    }


}
