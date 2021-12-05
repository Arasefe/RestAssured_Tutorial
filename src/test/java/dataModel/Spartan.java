package dataModel;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;

}
