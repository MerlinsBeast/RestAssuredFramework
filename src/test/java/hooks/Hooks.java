package hooks;

import io.cucumber.java.Before;
import stepDefinations.stepImplementation;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void BeforeScenario() throws IOException {
        System.out.println("insdei the hooks inple");
        stepImplementation st= new stepImplementation();
        if(stepImplementation.placeID==null){
            System.out.println("isnide hooks");
            st.addPlacePayloadWithAnd( "Urmila",  "Ambernath",  "Bhojpuri",  "https://www.automateIT.com",  "9894584985");
            st.userCallsWithMethod("ADDPlaceAPI", "POST");
            st.checkIsHitWithPlaceIDQueryparameter("");
        }

    }
}
