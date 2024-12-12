package vn.techzen.academy_pnv_12.Model;

public class Dictionary {
    private String word;
    private String translation;

    public Dictionary() {}

    public Dictionary(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}