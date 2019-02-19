package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;

class TeacherModel {

    String Titre;
    String val_titre;



    String Etablissement;
    String val_etab;

    String détail;
    String val_détail;

    String clé;
    String val_clé;

    public TeacherModel(String t, String val_t, String etab, String val_eta, String d, String val_d, String motclé , String val_motclé){
        this.Titre=t;
        this.val_titre=val_t;

        this.Etablissement=etab;
        this.val_etab=val_eta;
        this.détail=d;
        this.val_détail=val_d;
        this.clé=motclé;
        this.val_clé=val_motclé;

    }

    public void setTitre(String ti){ this.Titre=ti; }
    public String getTitre(){ return this.Titre ; }

    public void setVal_titre(String valti){ this.val_titre=valti; }
    public String getVal_titre(){ return this.val_titre ; }

    public void setEtablissement (String E){this.Etablissement=E;}
    public String getEtablissement() {return this.Etablissement;}

    public void setVal_etab (String val_e){this.val_etab=val_e;}
    public String getVal_etab(){return  this.val_etab;}

    public void setDétail(String det){this.détail=det;}
    public String getDétail(){return this.détail;}

    public void setVal_détail(String val_det){this.val_détail=val_det;}
    public String getVal_détail(){return this.val_détail;}

    public void setClé (String motc){this.clé=motc;}
    public String getClé(){return  this.clé;}

    public void setVal_clé (String val_motc){this.val_clé=val_motc;}
    public String getVal_clé(){return this.val_clé;}


}
