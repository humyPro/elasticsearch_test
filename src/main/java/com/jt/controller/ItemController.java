package com.jt.controller;

import com.jt.Service.ItemService;
import com.jt.pojo.Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ItemController {
    @Resource
    private ItemService service;

    @RequestMapping("search/{title}/{value}")
    public List<Item> searchItem(@PathVariable("title") String title, @PathVariable("value") String value) {
        List<Item> list = service.termSearch(title, value);
        return list;
    }
}
