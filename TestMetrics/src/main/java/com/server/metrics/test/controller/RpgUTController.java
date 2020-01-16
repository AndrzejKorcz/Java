package com.server.metrics.test.controller;

import com.server.metrics.test.model.Test;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class RpgUTController {

    @GetMapping(path = "/tests")
    public List<Test> list() {
        return TestStub.list();
    }
//    @GetMapping(value = "tests")
//    public Test create(@RequestBody Test test){
//        return  TestStub.create(test);
//    }
//    @GetMapping(value = "tests/{id}")
//    public  Test get(@PathVariable Long id) {
//        return TestStub.get(id);
//    }
//    @GetMapping(value = "tests/{id}")
//    public Test update(@PathVariable Long id, @RequestBody Test tests) {
//        return TestStub.update(id, tests);
//    }
//    @GetMapping(value = "shipwrecks/{id}")
//    public Test delete(@PathVariable Long id) {
//        return TestStub.delete(id);
//    }

}
