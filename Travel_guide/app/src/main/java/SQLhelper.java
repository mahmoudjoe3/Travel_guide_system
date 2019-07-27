import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLhelper extends SQLiteOpenHelper {

    SQLiteDatabase TravelDataBase;

    private static String DataBaseName="TravelGuide";
    private String CityTableCreation = "create table if not exists CityTable(CityID integer primary key autoincrement, CityName text)";
    private String SiteTableCreation = "create table if not exists SiteTable" +
            "(SiteID integer primary key autoincrement ," +
            " SiteName text not null , SiteDesc text not null , "+
            "C_Name text not null);";
    private String HotelTableCreation = "create table if not exists HotelTable" +
            "(HotelID integer primary key autoincrement ," +
            " HotelName text not null , HotelDesc text not null , "+
            "C_Name text);";
    private String FavouriteSiteCreation = "create table if not exists FavouriteSiteTeble(FavouriteSite text)";

    private String FavouriteHotelCreation = "create table if not exists FavouriteTable(FavouriteHotel text)";

    public SQLhelper(Context context) {
        super(context, DataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    //creation table
        sqLiteDatabase.execSQL(CityTableCreation);
        sqLiteDatabase.execSQL(SiteTableCreation);
        sqLiteDatabase.execSQL(HotelTableCreation);
        sqLiteDatabase.execSQL(FavouriteSiteCreation);
        sqLiteDatabase.execSQL(FavouriteHotelCreation);

        //CityTable
        ContentValues row = new ContentValues();
        row.put("CityName","Egypt");
        TravelDataBase.insert("CityTable",null,row);
        row.put("CityName","France");
        TravelDataBase.insert("CityTable",null,row);
        row.put("CityName","England");
        TravelDataBase.insert("CityTable",null,row);
        row.put("CityName","Italy");
        TravelDataBase.insert("CityTable",null,row);
        row.put("CityName","Germany");
        TravelDataBase.insert("CityTable",null,row);
        //CityTable

        //SiteTable
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('The Great Pyramids' , 'The last surviving of the Seven Wonders of the Ancient World, the Pyramids of Giza are one of the worlds most recognizable landmarks. Built as tombs for the mighty Pharaohs and guarded by the enigmatic Sphinx' , 'Egypt')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('Luxor Karnak Temple' , 'Famed for the Valley of the Kings, Karnak Temple, and the Memorial Temple of Hatshepsut, the Nile-side town of Luxor in Upper Egypt has a glut of tourist attractions.' , 'Egypt')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('St.Catherine Monastery' , 'One of the oldest monasteries in the world, St. Catherines stands at the foot of Mount Sinai, where Moses is said to have received the Ten Commandments.' , 'Egypt')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('Siwa Oasis' , ' Siwa is the tranquil tonic to the hustle of Egypts cities. This gorgeous little oasis, surrounded by date palm plantations and numerous fresh water springs' , 'Egypt')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('White Desert' , 'Egypts kookiest natural wonder is the White Desert, where surreally shaped chalk mountains have created what looks like a snowy wonderland in the middle of the arid sand.' , 'Egypt')");

        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'France')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'France')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'France')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'France')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'France')");

        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'England')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'England')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'England')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'England')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'England')");

        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Italy')");

        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into SiteTable (SiteName,SiteDesc,C_Name) values('' , '' , 'Germany')");
        //SiteTable

        //HotelTable
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Egypt')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Egypt')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Egypt')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Egypt')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Egypt')");

        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'France')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'France')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'France')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'France')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'France')");

        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'England')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'England')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'England')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'England')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'England')");

        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Italy')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Italy')");

        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Germany')");
        TravelDataBase.execSQL("insert into HotelTable (HotelName,HotelDesc,C_Name) values ('' , '' , 'Germany')");
        //HotelTable
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists CityTable");
        sqLiteDatabase.execSQL("drop table if exists SiteTable");
        sqLiteDatabase.execSQL("drop table if exists HotelTable");
        onCreate(sqLiteDatabase);

    }

    //show

    public Cursor ShowAllCities()
    {
        TravelDataBase = getReadableDatabase();
        String [] Detail = {"CityName"};
        Cursor cursor = TravelDataBase.query("CityTable",Detail,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }


    public Cursor GetSites(String City)
    {
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery("select * from SiteTable where C_Name like?",new String[]{"%"+City+"%"});

        if(cursor.getCount() != 0)
        {
            cursor.moveToFirst();
            TravelDataBase.close();
            return cursor;
        }
        TravelDataBase.close();
        return cursor;
    }

    public Cursor GetHotels(String City)
    {
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery("select * from HotelTable where C_Name like?",new String[]{"%"+City+"%"});

        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            TravelDataBase.close();
            return cursor;
        }
        TravelDataBase.close();
        return null;
    }

    //favouriteSite

    public void AddFavouriteSite(String Site)
    {
        ContentValues row = new ContentValues();
        row.put("FavouriteSite",Site);;
        TravelDataBase = getWritableDatabase();
        TravelDataBase.insert("FavouriteSiteTable",null,row);
        TravelDataBase.close();
    }
    public void AddFavouriteHotel(String Hotel)
    {
        ContentValues row = new ContentValues();
        row.put("FavouriteHotel",Hotel);;
        TravelDataBase = getWritableDatabase();
        TravelDataBase.insert("FavouriteHotelTable",null,row);
        TravelDataBase.close();
    }

    //showfavourite

    public Cursor ShowAllFavouriteSites()
    {
        TravelDataBase = getReadableDatabase();
        String [] Detail = {"FavouriteSite"};
        Cursor cursor = TravelDataBase.query("FavouriteSiteTable",Detail,null,null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return cursor;
    }
    public Cursor ShowAllFavouriteHotels() {
        TravelDataBase = getReadableDatabase();
        String[] Detail = {"FavouriteHotel"};
        Cursor cursor = TravelDataBase.query("FavouriteHotelTable", Detail, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }
    }
