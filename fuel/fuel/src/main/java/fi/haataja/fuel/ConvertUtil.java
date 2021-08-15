package fi.haataja.fuel;

public class ConvertUtil {
    public static String getMonth(int month){
        String monthName = "";
        switch (month){
            case 1:
                monthName = "Tammikuu";
                break;
            case 2:
                monthName = "Helmikuu";
                break;
            case 3:
                monthName = "Maaliskuu";
                break;
            case 4:
                monthName = "Huhtikuu";
                break;
            case 5:
                monthName = "Toukokuu";
                break;
            case 6:
                monthName = "Kesäkuu";
                break;
            case 7:
                monthName = "Heinäkuu";
                break;
            case 8:
                monthName = "Elokuu";
                break;
            case 9:
                monthName = "Syyskuu";
                break;
            case 10:
                monthName = "Lokakuu";
                break;
            case 11:
                monthName = "Marraskuu";
                break;
            case 12:
                monthName = "Joulukuu";
                break;
        }

        return monthName;
    }
}
