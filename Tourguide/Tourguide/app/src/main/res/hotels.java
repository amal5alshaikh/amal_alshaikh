/**
 * Created by Amal Alshaikh on 16/8/2017.
 */

public class information {
    private String minformationName;
    private String minformationdescraption;
    private int minformationImage;


    public information(String informationName, String informationscraption, int informationimage) {
        minformationName = informationName;
        minformationdescraption = informationscraption;
        minformationImage = informationimage;


    }

    public String getinformationName() {
        return minformationName;
    }

    public String getinformationdescraption() {
        return minformationdescraption;
    }

    public int getinformationimage() {
        return minformationImage;
    }


}
