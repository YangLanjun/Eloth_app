package cn.yanglj65.www.ecloth_app.Entity;

public class Pants {
    public Cloth pant;

    public Pants(Cloth cloth) {
        pant = cloth;
    }

    public Pants() {
        pant = new Cloth();
    }
}
