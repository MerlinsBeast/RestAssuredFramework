package resources;
import excelDrivingUtility.ExcelReader;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild  extends ExcelReader {

    public AddPlace addPlacePayLoad(String Name, String Address, String Language, String WebSite, String PhoneNumber){
        List<String> list = new ArrayList(List.of("showPark","shop"));

        Location locate = new Location();
        locate.setLat((Double) getData(0,1,4));
        locate.setLng((Double) getData(0,1,5));

        AddPlace addplace = new AddPlace();
        addplace.setAccuracy((Double) getData(0,1,7));
        addplace.setAddress(Address);
        addplace.setLanguage(Language);
        addplace.setPhone_number(PhoneNumber);
        addplace.setWebsite((String) getData(0,1,8));
        addplace.setName(Name);
        addplace.setTypes(list);
        addplace.setLocation(locate);

        return addplace;
    }
    public String deletePlacePayload(String placeID){
//    return "{\"place_id\":"+placeID+"}";
   return  "{\r\n     \"place_id\":\""+placeID+"\"\r\n}";
    }
}
