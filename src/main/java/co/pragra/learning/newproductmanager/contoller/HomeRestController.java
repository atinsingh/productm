package co.pragra.learning.newproductmanager.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeRestController {
    @GetMapping("/api")
    public Map<String, String> home() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "My First Rest APi");
        map.put("heroTitle", "MyFist API Title");
        return map;
    }
}
