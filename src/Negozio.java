public class Negozio {
    private String name;
    private String owner;
    private String typeOfShop;
    private static final int minCapacity = 0;
    private static final int maxCapacity = 100;
    private static int numberOfShops;

    private static void keepTrackOfShopNumber() {numberOfShops ++;}

    public Negozio (String name, String owner, String typeOfShop){
        this.name = name;
        this.owner = owner;
        this.typeOfShop= typeOfShop;

    }

    public String getName(){
        return name;
    }

    public String getOwner(){
        return owner;
    }

    public String getTypeOfShop(){
        return typeOfShop;
    }



}
