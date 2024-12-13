package vn.techzen.academy_pnv_12.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techzen.academy_pnv_12.Model.Dictionary;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DictionaryController {
    private Map<String, String> dictionary = new HashMap<>();

    public DictionaryController() {
        dictionary.put("hello", "xin chào");
        dictionary.put("apple", "quả táo");
        dictionary.put("banana", "quả chuối");
        dictionary.put("orange", "quả cam");
        dictionary.put("lemon", "quả chanh");
        dictionary.put("melon", "quả dưa");
        dictionary.put("watermelon", "quả dưa hấu");
        dictionary.put("blueberry", "quả việt quất");
    }

    @GetMapping("/dictionary/search")
    public ResponseEntity<Dictionary> searchDictionary(@RequestParam String word) {
        if (dictionary.containsKey(word)) {
            return ResponseEntity.ok(new Dictionary(word, dictionary.get(word)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
