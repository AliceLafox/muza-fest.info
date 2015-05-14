package net.lafox.muza.controller;

import net.lafox.muza.entity.Image;
import net.lafox.muza.entity.Item;
import net.lafox.muza.service.ImageService;
import net.lafox.muza.service.ItemService;
import net.lafox.muza.validator.ItemForm;
import net.lafox.muza.validator.ValidationResponse;
import net.lafox.usr.UserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.math.NumberUtils.toLong;

@RestController
@RequestMapping("json/cab")
public class JsonCabController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(JsonCabController.class);

    @Qualifier("itemServiceImpl")
    @Autowired
    private ItemService itemService;


    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("imageServiceImpl")
    @Autowired
    private ImageService imageService;

    @RequestMapping("list")
    public Map getItemList(HttpServletRequest request
            , @RequestParam(value = "search[value]", required = false, defaultValue = "") String searchText
    ) {

        Criterion criterionSearch = null;
        if (searchText != null && !searchText.isEmpty()) {
            criterionSearch = Restrictions.or(
                    Restrictions.eq("id", toLong(searchText)),
                    Restrictions.like("title", "%" + searchText + "%")
            );
        }

        Map map = itemService.getListForDataTable(request.getParameterMap()
                , Restrictions.eq("user", userService.getCurrentUser())
                , criterionSearch
        );
        logger.debug("map:", map);

        return map;

    }

    @RequestMapping(value = "save")
    public ValidationResponse saveForm(@Valid ItemForm itemForm, BindingResult result) {
        result.getAllErrors();
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors()) {
            validationResponse.setErrorMessageList(result.getAllErrors());
        } else {
            try {
                itemService.saveFormData(itemForm);
                validationResponse.setStatus("SUCCESS");
            } catch (Exception e) {
                validationResponse.setStatus("FAIL");
                validationResponse.setErrorMessageList(Collections.singletonList(new ObjectError("itemForm", e.getMessage())));
            }
        }
        return validationResponse;
    }

    @RequestMapping(value = "addItem")
    public Map addItem(@RequestParam String title) {
        Map<String, Object> map = new HashMap<>();
        try {
            Item item = new Item();
            item.setTitle(title);
            item.setUser(userService.getCurrentUser());
            item.setEnabled(false);
            itemService.insert(item);
            map.put("item", item);
            map.put("status", "OK");
        } catch (Exception e) {
            map.put("status", "FAIL");
            map.put("message", e.getMessage());
            logger.error(title);
        }
        return map;

    }

    @RequestMapping(value = "uploadItemAvatar", method = RequestMethod.POST)
    public Map<String, Object> uploadItemAvatar(@RequestParam("id") long id, @RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            Item item = itemService.get(id);
            Image avatar = item.getAvatar();
            if (avatar == null) {
                avatar = new Image(file.getBytes());
                imageService.insert(avatar);
            } else {
                avatar.setImg(file.getBytes());
                imageService.update(avatar);
            }
            item.setAvatar(avatar);
            itemService.update(item);
            map.put("avatar", avatar);
        } catch (Exception e) {
            map.put("error", e.getMessage());
            logger.error(e.getMessage());
        }
        return map;
    }
}
