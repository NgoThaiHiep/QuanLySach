
package Entity;

/**
 *
 * @author FPTSHOP
 */
public class Thue {
    private String id;
    private float thue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Thue(String id, float thue) {
        this.id = id;
        this.thue = thue;
    }

   
    public Thue() {
    }

  

    public float getThue() {
        return thue;
    }

    public void setThue(float thue) {
        this.thue = thue;
    }
    
    
}
