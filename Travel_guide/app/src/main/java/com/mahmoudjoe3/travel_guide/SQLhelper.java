package com.mahmoudjoe3.travel_guide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DataBaseName = "TravelDataBase";
    private static final int DataBaseVersion = 1;
    private SQLiteDatabase TravelDataBase;

    private static final String CityTable_Name = "CityTable";
    private static final String CityColumn_ID = "CityID";
    private static final String CityColumn_Name = "CityName";
    private static final String CityTable_Creation = "create table if not exists CityTable (CityID integer primary key autoincrement ," +
            "CityName text)";

    private static final String SiteTable_Name = "SiteTable";
    public static final String SiteColumn_ID = "SiteID";
    private static final String SiteColumn_Name = "SiteName";
    private static final String SiteColumn_Desc = "SiteDesc";
    private static final String SiteColumn_CName = "C_Name";
    private static final String SiteTable_Creation = "create table if not exists SiteTable (SiteID integer primary key autoincrement ," +
            "SiteName text , SiteDesc text , C_Name text)";

    private static final String HotelTable_Name = "HotelTable";
    public static final String HotelColumn_ID = "HotelID";
    private static final String HotelColumn_Name = "HotelName";
    private static final String HotelColumn_Desc = "HotelDesc";
    private static final String HotelColumn_CName = "C_Name";
    private static final String HotelTable_Creation = "create table if not exists HotelTable (HotelID integer primary key autoincrement ," +
            "HotelName text , HotelDesc text , C_Name text)";

    private String FavouriteSiteCreation = "create table if not exists FavouriteSiteTable(ID integer primary key autoincrement,FavouriteSite text)";
    private String FavouriteHotelCreation = "create table if not exists FavouriteHotelTable(ID integer primary key autoincrement,FavouriteHotel text)";

    public SQLiteHelper(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CityTable_Creation);
        sqLiteDatabase.execSQL(SiteTable_Creation);
        sqLiteDatabase.execSQL(HotelTable_Creation);
        sqLiteDatabase.execSQL(FavouriteSiteCreation);
        sqLiteDatabase.execSQL(FavouriteHotelCreation);
        //Cities
        ContentValues row = new ContentValues();
        row.put(CityColumn_Name,"Egypt");
        sqLiteDatabase.insert(CityTable_Name,null,row);
        row.put(CityColumn_Name,"France");
        sqLiteDatabase.insert(CityTable_Name,null,row);
        row.put(CityColumn_Name,"England");
        sqLiteDatabase.insert(CityTable_Name,null,row);
        row.put(CityColumn_Name,"Italy");
        sqLiteDatabase.insert(CityTable_Name,null,row);
        row.put(CityColumn_Name,"Germany");
        sqLiteDatabase.insert(CityTable_Name,null,row);
        //Cities

        //Sites
        ContentValues Site = new ContentValues();
        Site.put(SiteColumn_Name,"The Great Pyramids");
        Site.put(SiteColumn_Desc,"The last surviving of the Seven Wonders of the Ancient World, the Pyramids of Giza are one of the worlds most recognizable landmarks. Built as tombs for the mighty Pharaohs and guarded by the enigmatic Sphinx");
        Site.put(SiteColumn_CName,"Egypt");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Luxor's Karnak Temple");
        Site.put(SiteColumn_Desc,"Famed for the Valley of the Kings, Karnak Temple, and the Memorial Temple of Hatshepsut, the Nile-side town of Luxor in Upper Egypt has a glut of tourist attractions.");
        Site.put(SiteColumn_CName,"Egypt");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"St.Catherine Monastery");
        Site.put(SiteColumn_Desc,"One of the oldest monasteries in the world, St. Catherines stands at the foot of Mount Sinai, where Moses is said to have received the Ten Commandments.");
        Site.put(SiteColumn_CName,"Egypt");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Siwa Oasis");
        Site.put(SiteColumn_Desc,"Siwa is the tranquil tonic to the hustle of Egypts cities. This gorgeous little oasis, surrounded by date palm plantations and numerous fresh water springs");
        Site.put(SiteColumn_CName,"Egypt");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"White Desert");
        Site.put(SiteColumn_Desc,"Egypt's kookiest natural wonder is the White Desert, where surreally shaped chalk mountains have created what looks like a snowy wonderland in the middle of the arid sand.");
        Site.put(SiteColumn_CName,"Egypt");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);

        Site.put(SiteColumn_Name,"Eiffel Tower");
        Site.put(SiteColumn_Desc,"The symbol of Paris, the Eiffel Tower is one of the world's most famous landmarks");
        Site.put(SiteColumn_CName,"France");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Louvre Museum");
        Site.put(SiteColumn_Desc,"n the former royal palace of French Kings, the Louvre is an incomparable museum that ranks among the top European collections of fine arts");
        Site.put(SiteColumn_CName,"France");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Palace of Versailles");
        Site.put(SiteColumn_Desc,"More than just a royal residence, Versailles was designed to show off the glory of the French monarchy");
        Site.put(SiteColumn_CName,"France");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Mont Saint-Michel");
        Site.put(SiteColumn_Desc,"Rising dramatically out of the sea on the coast of Normandy, Mont Saint-Michel is one of France's most striking landmarks");
        Site.put(SiteColumn_CName,"France");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Loire Valley Châteaux");
        Site.put(SiteColumn_Desc,"Traveling through the Loire Valley feels like turning the pages of a children's storybook");
        Site.put(SiteColumn_CName,"France");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);

        Site.put(SiteColumn_Name,"Stonehenge");
        Site.put(SiteColumn_Desc,"Stonehenge, 10 miles north of Salisburyon Salisbury Plain, is Europe's best-known prehistoric monument");
        Site.put(SiteColumn_CName,"England");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Tower of London");
        Site.put(SiteColumn_Desc,"Prison, palace, treasure vault, observatory, and menagerie - the Tower of London has done it all. Widely considered the most important building in England");
        Site.put(SiteColumn_CName,"England");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"The Roman Baths and Georgian City of Bath");
        Site.put(SiteColumn_Desc,"This remarkably beautiful city boasts more fantastic tourist attractions than you could hope to visit in a day");
        Site.put(SiteColumn_CName,"England");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Canterbury Cathedral");
        Site.put(SiteColumn_Desc,"Located in the heart of the historic city that bears its name, Canterbury Cathedral (a UNESCO World Heritage Site) is home to the Archbishop of Canterbury and is the cradle of English Christianity");
        Site.put(SiteColumn_CName,"England");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Eden Project");
        Site.put(SiteColumn_Desc,"The incredible Eden Project is a collection of unique artificial biomes containing an amazing collection of plants from around the world");
        Site.put(SiteColumn_CName,"England");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);

        Site.put(SiteColumn_Name,"Colosseum");
        Site.put(SiteColumn_Desc,"This huge Amphitheater is the largest of its kind ever built by the Roman Empire and has remained a model for sports facilities right up to modern times");
        Site.put(SiteColumn_CName,"Italy");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Venice Canals");
        Site.put(SiteColumn_Desc,"A gondola ride through the canals of Venice is a tradition that travelers have been enjoying for centuries");
        Site.put(SiteColumn_CName,"Italy");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Pompeii");
        Site.put(SiteColumn_Desc,"The still smoking volcano of Mt. Vesuvius looks down on the remains of the city it destroyed in AD 79");
        Site.put(SiteColumn_CName,"Italy");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Leaning Tower of Pisa");
        Site.put(SiteColumn_Desc,"The Leaning Tower of Pisa is actually just one of many attractions in the city of Pisa, but its fame, gained from its flaw, is world renown");
        Site.put(SiteColumn_CName,"Italy");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Vatican City");
        Site.put(SiteColumn_Desc,"The Vatican is home to some of the world's most priceless art and art collections. The centerpiece is the great Basilica of St. Peter, with the tomb of St. ");
        Site.put(SiteColumn_CName,"Italy");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);

        Site.put(SiteColumn_Name,"Editor's PickBerlin's Brandenburg Gate");
        Site.put(SiteColumn_Desc,"Modeled on the Acropolis in Athens and built for King Frederick William II in 1791, the monumental sandstone Brandenburg Gate in Berlin's Mitte district was the city's first Neoclassical structure");
        Site.put(SiteColumn_CName,"Germany");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Cologne Cathedral (Kölner Dom)");
        Site.put(SiteColumn_Desc,"the Cathedral of St. Peter and St. Mary - is located on the banks of the Rhine and is undoubtedly Cologne's most impressive landmark");
        Site.put(SiteColumn_CName,"Germany");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"The Black Forest");
        Site.put(SiteColumn_Desc,"The beautiful Black Forest with its dark, densely-wooded hills is one of the most visited upland regions in all of Europe");
        Site.put(SiteColumn_CName,"Germany");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"The Ultimate Fairytale Castle: Neuschwanstein");
        Site.put(SiteColumn_Desc,"The quaint old town of Füssen, situated between the Ammergau and Allgäu Alps and a popular alpine resort and winter sports center, is a good base from which to explore nearby Neuschwanstein Castle, one of Europe's most famous (and picturesque) royal castles.\n");
        Site.put(SiteColumn_CName,"Germany");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        Site.put(SiteColumn_Name,"Berlin's Museum Island");
        Site.put(SiteColumn_Desc,"Berlin's world-famous Museumsinsel, or Museum Island, lies between the River Spree and the Kupfergraben - a 400-meter-long canal off the river - and includes many of the city's oldest and most important museums");
        Site.put(SiteColumn_CName,"Germany");
        sqLiteDatabase.insert(SiteTable_Name,null,Site);
        //Sites

        //Hotels
        ContentValues Hotel = new ContentValues();


        Hotel.put(HotelColumn_Name,"Fairmont Nile City, Cairo");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 126$/Day\"");
        Hotel.put(HotelColumn_CName,"Egypt");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);
        Hotel.put(HotelColumn_Name,"Aqua Hotel Resort & Spa, Sharm El-Sheikh");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 147$/Day");
        Hotel.put(HotelColumn_CName,"Egypt");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);


        Hotel.put(HotelColumn_Name,"Disneyland Hotel, Chessy, Seine-et-Marne");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 408$/Day");
        Hotel.put(HotelColumn_CName,"France");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);
        Hotel.put(HotelColumn_Name,"Kanopée Village - Campground, Trévoux, Ain");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 71$/Day");
        Hotel.put(HotelColumn_CName,"France");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);


        Hotel.put(HotelColumn_Name,"Karma St. Martin's Hotel, Lower Town, Isles of Scilly");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 142$/Day");
        Hotel.put(HotelColumn_CName,"England");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);
        Hotel.put(HotelColumn_Name,"Village House Hotel - Inn, Findon");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 46$/Day");
        Hotel.put(HotelColumn_CName,"England");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);


        Hotel.put(HotelColumn_Name,"Strand Hotel Terme Delfini, Ischia");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 36$/Day");
        Hotel.put(HotelColumn_CName,"Italy");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);
        Hotel.put(HotelColumn_Name,"Villa Casale");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 100$/Day");
        Hotel.put(HotelColumn_CName,"Italy");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);


        Hotel.put(HotelColumn_Name,"LEGOLAND Feriendorf, Günzburg");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 149$/Day");
        Hotel.put(HotelColumn_CName,"Germany");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);
        Hotel.put(HotelColumn_Name,"Hotel Ling Bao, Walberberg, Bornheim");
        Hotel.put(HotelColumn_Desc,"5 Stars Hotel, You Can Book now a Room for 245$/Day");
        Hotel.put(HotelColumn_CName,"Germany");
        sqLiteDatabase.insert(HotelTable_Name,null,Hotel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists "+ CityTable_Name);
        sqLiteDatabase.execSQL("drop table if exists "+ SiteTable_Name);
        onCreate(sqLiteDatabase);
    }
    public Cursor GetAllCities()
    {
        String query = "select * from " + CityTable_Name;
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor GetSites(String City)
    {
        String [] args = {City};
        String query = "select * from SiteTable where C_Name like ?";
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery(query,args);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor GetHotels(String City)
    {
        String [] args = {City};
        String query = "select * from HotelTable where C_Name like ?";
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery(query,args);
        cursor.moveToFirst();
        return cursor;
    }
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
    public Cursor ShowAllFavouriteSites()
    {
        String query = "select * from FavouriteSiteTable";
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor ShowAllFavouriteHotels()
    {
        String query = "select * from FavouriteHotelTable";
        TravelDataBase = getReadableDatabase();
        Cursor cursor = TravelDataBase.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor;
    }
}