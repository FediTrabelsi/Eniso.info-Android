package fedi.trabelsi.tp2.ia2.eniso.eniso;

public class Active {
    private String userFullTitle ;
    private String iconURL ;
    private String userTypeName ;

    public String getUserFullTitle() {
        return userFullTitle;
    }

    public String getIconURL() {
        return iconURL;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public Active(String userFullTitle, String iconURL, String userTypeName) {
        this.userFullTitle = userFullTitle;
        this.iconURL = iconURL;
        this.userTypeName = userTypeName;
    }


}
