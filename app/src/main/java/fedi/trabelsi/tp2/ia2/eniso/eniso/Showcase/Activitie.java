package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.app.Activity;

public class Activitie {
    public String imgurl;
    public String activitietitle;
    public String activitiedesc;

    public Activitie(String i,String t,String d){
        this.imgurl=i;
        this.activitietitle=t;
        this.activitiedesc=d;

    }

    public void setImgurl(String url){
        this.imgurl=url;
    }

    public String getImgurl(){
        return this.imgurl;
    }

    public void setActivitietitle(String title){
        this.activitietitle=title;
    }

    public String getActivitietitle(){
        return this.activitietitle;
    }

    public void setActivitiedesc(String desc){
        this.activitiedesc=desc;
    }

    public String getActivitiedesc(){
        return this.activitiedesc;
    }
}
