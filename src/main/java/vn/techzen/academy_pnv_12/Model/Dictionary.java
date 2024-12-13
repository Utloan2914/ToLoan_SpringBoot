package vn.techzen.academy_pnv_12.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dictionary {
    private String word;
    private String translation;

    public Dictionary() {}

    public Dictionary(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

}