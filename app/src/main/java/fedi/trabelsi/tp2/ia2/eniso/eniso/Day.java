package fedi.trabelsi.tp2.ia2.eniso.eniso;

import java.util.ArrayList;

public class Day {
    private ArrayList<Session> sessions = new ArrayList();

    public Day() {

    }

    public  void  addSession(Session s){
        sessions.add(s);
    }

    public  Session getSession(int position){
        return sessions.get(position);
    }


}
