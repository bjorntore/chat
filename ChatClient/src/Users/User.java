package Users;

/**
 *
 * @author BjørnTore
 */
public class User {

    private String name, signature;

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return name;
    }
}
