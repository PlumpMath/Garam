package co.mitsuha.lockscreenlearn;

/**
 * Created by studiodoth on 2017. 5. 18..
 */

public class nmListViewItem {
    private String title;
    private String type;
    private String password;
    private int id;

    public void setTitle(String Title) {
        title = Title;
    }
    public void setType(String Type) {
        type = Type;
    }
    public void setPassword(String Password) { password = Password; }
    public void setID(int ID) {
        id = ID;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
    public String getPassword() { return password; }
    public int getID() {
        return id;
    }
}
