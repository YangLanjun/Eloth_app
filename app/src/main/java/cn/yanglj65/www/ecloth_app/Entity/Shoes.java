package cn.yanglj65.www.ecloth_app.Entity;

public class Shoes {
    public Cloth shoes;
    public Shoes(Cloth cloth){
        shoes=cloth;
    }
    public Shoes(){
        shoes=new Cloth();
    }
}
