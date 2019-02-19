package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;

public class IdentityModel {
    String titre;
    String valuere;

    public IdentityModel(String t, String v){
        this.titre=t;
        this.valuere=v;
    }

    public void setTitre(String ti){
        this.titre=ti;
    }

    public String getTitre(){
        return this.titre;
    }

    public void setValuere(String val){
        this.valuere=val;
    }

    public String getValuere(){
       return this.valuere;
    }


}
