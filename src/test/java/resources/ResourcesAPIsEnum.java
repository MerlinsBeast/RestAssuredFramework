package resources;

public enum ResourcesAPIsEnum {
    ADDPlaceAPI("/maps/api/place/add/json"),
    GETPlaceAPI("/maps/api/place/get/json"),
    DELETEPlaceAPI("/maps/api/place/delete/json");
    private String resource;
    ResourcesAPIsEnum(String resource) {
    this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
