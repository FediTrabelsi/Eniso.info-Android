package fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome;

public class PressModel {
    private String titre;
    private String contenu;
    private String sender;

    public PressModel(String title,String content,String sender){
        this.titre=title;
        this.contenu=content;
        this.sender=sender;
    }

    public void setTitre(String t){
        this.titre=t;
    }

    public String getTitre(){
        return this.titre;
    }

    public void setContenu(String c){
        this.contenu=c;
    }

    public String getContenu(){
        return this.contenu;
    }

    public void setSender(String s){
        this.sender=s;
    }
    public String getSender(){
        return this.sender;
    }

}
