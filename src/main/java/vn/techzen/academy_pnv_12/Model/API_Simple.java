package vn.techzen.academy_pnv_12.Model;

public class API_Simple {
    private int id;
    private String name;
    public API_Simple(int id, String name, double age) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
