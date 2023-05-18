package People;

public class admin {
    private Integer adminID;
    private String adminName;
    private String adminPassWord;

    public admin(){}
    public admin(Integer adminID, String adminName, String adminPassWord){
        this.adminID=adminID;
        this.adminName=adminName;
        this.adminPassWord=adminPassWord;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassWord() {
        return adminPassWord;
    }

    public void setAdminPassWord(String adminPassWord) {
        this.adminPassWord = adminPassWord;
    }

    @Override
    public String toString() {
        return "admin{" +
                "adminID=" + adminID +
                ", adminName='" + adminName + '\'' +
                ", adminPassWord='" + adminPassWord + '\'' +
                '}';
    }
}
