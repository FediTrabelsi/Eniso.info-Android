package fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section;

public class GroupModel {
    private String project,equipe,coach,team,members,desc,owner;

    public GroupModel(String eq,String coach,String team,String members,String ds,String ow){

        this.desc=ds;
        this.owner=ow;
        this.equipe=eq;
        this.coach=coach;
        this.team=team;
        this.members=members;
    }



    public String getEquipe(){
        return equipe;
    }

    public void setEquipe(String equipe){
        this.equipe=equipe;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner=owner;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc=desc;
    }

    public String getCoach(){
        return coach;
    }

    public void setCoach(String coach){
        this.coach=coach;
    }

    public String getTeam(){
        return team;
    }

    public void setTeam(String team){
        this.team=team;
    }

    public String getMembers(){
        return this.members;
    }

    public void setMembers(String members){
        this.members=members;
    }


}
