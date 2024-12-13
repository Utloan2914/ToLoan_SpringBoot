package vn.techzen.academy_pnv_12.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class API_Simple {
    private int id;
    private String name;
    public API_Simple(int id, String name, double age) {
        this.id = id;
        this.name = name;
    }
}
