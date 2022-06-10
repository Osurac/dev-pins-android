package es.ucm.fdi.devpins.data.model;

public class User {

    private int id;
    private String email;
    private String password;

    /**
     * Constructor user
     * @param id
     * @param email
     * @param password
     */
    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * función get de id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * función set de id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * fuición get de email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * función set de email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * función get de password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *fuinción set de password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
