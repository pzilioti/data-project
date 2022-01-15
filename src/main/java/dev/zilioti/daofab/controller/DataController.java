package dev.zilioti.daofab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zilioti.daofab.entity.Parent;
import dev.zilioti.daofab.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/api")
public class DataController {

    @Autowired
    DataService service;

    @GetMapping(path = "/parent")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getParents(@RequestParam Integer page, @RequestParam String sort){
        if(sort.equals("asc") || sort.equals("desc")){
            return ResponseEntity.ok().body(service.getParents(page, sort));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Sort must be either asc or desc");
        return ResponseEntity.badRequest().body(map);
    }

    @GetMapping(path = "/child")
    public @ResponseBody ResponseEntity<List<Map<String, Object>>> getChildren(){
        return ResponseEntity.ok().body(service.getChildren());
    }
}
