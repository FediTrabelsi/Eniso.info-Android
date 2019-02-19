package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

public class Feacher {
    private String text;
    private String srcimg;

    public Feacher(String text,String url){
        this.text=text;
        this.srcimg=url;
    }

    public void setText(String t ){
        this.text=t;
    }

    public String getText(){
        return this.text;
    }

    public void setSrcimg(String u){
        this.srcimg=u;
    }

    public String getSrcimg(){
        return this.srcimg;
    }
}
