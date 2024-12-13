package vn.techzen.academy_pnv_12.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("greeting")
public class APISimpleController {
    @GetMapping
    public String greeting(@RequestParam(defaultValue = "") String name) {
        return String.format("Hello %s!!!", name);
    }
}
