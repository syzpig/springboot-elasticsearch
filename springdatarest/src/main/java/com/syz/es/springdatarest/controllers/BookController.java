package com.syz.es.springdatarest.controllers;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syz.es.springdatarest.models.Book;
import com.syz.es.springdatarest.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: syz
 * @Date: 2021/11/8 12:22
 * @Description:
 */

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    EsService esService;

    @RequestMapping(value = "/add_index", method = RequestMethod.POST)
    public ResponseEntity<String> indexDoc(@RequestBody Book book) {
        System.out.println("book===" + book);
        Object ob = esService.saveIndex(book);
        String json = JSON.toJSONString(ob);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<String> getById(@RequestParam("id") String id) {
        Object ob = esService.getById(id);
        String json = JSON.toJSONString(ob);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<String> getByName(@RequestParam("name") String name) {
        Object ob = esService.getByName(name);
        String json = JSON.toJSONString(ob);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
