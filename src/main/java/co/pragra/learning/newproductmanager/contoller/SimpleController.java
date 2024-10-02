package co.pragra.learning.newproductmanager.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SimpleController {

    @GetMapping("/api/dummy/{name}")
    public List<String> justDummy(
            @RequestParam(value = "cost" , required = false) Double cost,
            @RequestParam(value = "hello", required = false, defaultValue = "world") String hello,
            @PathVariable String name
    ){
        System.out.println("cost = " + cost);
        System.out.println("hello = " + hello);
        return Arrays.asList("done");

    }
}
