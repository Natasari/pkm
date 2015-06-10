package com.example.penisriwahyu.pointerb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.penisriwahyu.pointerb.Model.Bab;
import com.example.penisriwahyu.pointerb.Model.Kelas;
import com.example.penisriwahyu.pointerb.Model.Mapel;
import com.example.penisriwahyu.pointerb.Model.Materi;
import com.example.penisriwahyu.pointerb.Model.PaketSoal;
import com.example.penisriwahyu.pointerb.Model.Soal;
import com.example.penisriwahyu.pointerb.Model.Subbab;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karsten on 09/03/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pointerschool";

    // Tables Names
    private static final String TABLE_BAB = "bab";
    private static final String TABLE_KELAS = "kelas";
    private static final String TABLE_MAPEL = "mapel";
    private static final String TABLE_MATERI = "materi";
    private static final String TABLE_PAKET_SOAL = "paket_soal";
    private static final String TABLE_SOAL = "soal";
    private static final String TABLE_SUBBAB = "subbab";

    // Bab Table Columns names
    private static final String KEY_ID_BAB = "id_bab";
    //private static final String KEY_ID_KELAS = "id_kelas";
    //private static final String KEY_ID_MAPEL = "id_mapel";
    private static final String KEY_NOMOR_BAB = "nomor_bab";
    private static final String KEY_NAMA_BAB = "nama_bab";

    // Kelas Table Columns names
    private static final String KEY_ID_KELAS = "id_kelas";
    private static final String KEY_NOMOR_KELAS = "nomor_kelas";
    private static final String KEY_NAMA_KELAS = "nama_kelas";

    // Mapel Table Columns names
    private static final String KEY_ID_MAPEL = "id_mapel";
    private static final String KEY_NAMA_MAPEL = "nama_mapel";

    // Materi Table Columns names
    private static final String KEY_ID_MATERI = "id_materi";
    //private static final String KEY_ID_SUBBAB = "id_subbab";
    private static final String KEY_NOMOR_MATERI = "nomor_materi";
    private static final String KEY_ID_FILE_MATERI = "id_file_materi";
    private static final String KEY_CATATAN_MATERI = "catatan_materi";

    // Paket_Soal Table Columns names
    private static final String KEY_ID_PAKET_SOAL = "id_paket_soal";
    //private static final String KEY_ID_SUBBAB = "id_subbab";
    private static final String KEY_NOMOR_PAKET_SOAL = "nomor_paket_soal";
    private static final String KEY_NAMA_PAKET_SOAL = "nama_paket_soal";
    private static final String KEY_LEVEL_SOAL = "level_soal";

    // Soal Table Columns names
    private static final String KEY_ID_SOAL = "id_soal";
    //private static final String KEY_ID_PAKET_SOAL = "id_paket_soal";
    private static final String KEY_NOMOR_SOAL = "nomor_soal";
    private static final String KEY_ID_FILE_SOAL = "id_file_soal";
    private static final String KEY_PILIHAN1 = "pilihan1";
    private static final String KEY_PILIHAN2 = "pilihan2";
    private static final String KEY_PILIHAN3 = "pilihan3";
    private static final String KEY_PILIHAN4 = "pilihan4";
    private static final String KEY_PILIHAN5 = "pilihan5";
    private static final String KEY_KUNCI_SOAL = "kunci_soal";
    private static final String KEY_PEMBAHASAN_SOAL = "pembahasan_soal";
    private static final String KEY_CATATAN_SOAL = "catatan_soal";

    //Subbab Table Columns names
    private static final String KEY_ID_SUBBAB = "id_subbab";
    //private static final String KEY_ID_BAB = "id_bab";
    private static final String KEY_NOMOR_SUBBAB = "nomor_subbab";
    private static final String KEY_NAMA_SUBBAB = "nama_subbab";

    // ----||  Table Create Statements  ||----
    // KELAS table create statement
    private static final String CREATE_TABLE_KELAS = "CREATE TABLE "
            + TABLE_KELAS + "(" + KEY_ID_KELAS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NOMOR_KELAS + " INTEGER," +  KEY_NAMA_KELAS  + " TEXT" + ")";

    // MAPEL table create statement
    private static final String CREATE_TABLE_MAPEL = "CREATE TABLE "
            + TABLE_MAPEL + "(" + KEY_ID_MAPEL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +  KEY_NAMA_MAPEL  + " TEXT" + ")";

    // BAB table create statement
    private static final String CREATE_TABLE_BAB = "CREATE TABLE " + TABLE_BAB
            + "(" + KEY_ID_BAB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ID_KELAS + " INTEGER,"
            + KEY_ID_MAPEL + " INTEGER,"
            + KEY_NOMOR_BAB + " INTEGER,"
            + KEY_NAMA_BAB + " TEXT,"
            + " FOREIGN KEY(" + KEY_ID_KELAS + ") REFERENCES " + TABLE_KELAS + "(" + KEY_ID_KELAS + "),"
            + " FOREIGN KEY(" + KEY_ID_MAPEL + ") REFERENCES " + TABLE_MAPEL + "(" + KEY_ID_MAPEL + "))";

    // SUBBAB table create statement
    private static final String CREATE_TABLE_SUBBAB = "CREATE TABLE " + TABLE_SUBBAB
            + "(" + KEY_ID_SUBBAB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ID_BAB + " INTEGER,"
            + KEY_NOMOR_SUBBAB + " INTEGER,"
            + KEY_NAMA_SUBBAB + " TEXT,"
            + " FOREIGN KEY(" + KEY_ID_BAB + ") REFERENCES " + TABLE_BAB + "(" + KEY_ID_BAB + "))";

    // MATERI table create statement
    private static final String CREATE_TABLE_MATERI = "CREATE TABLE " + TABLE_MATERI
            + "(" + KEY_ID_MATERI + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ID_SUBBAB + " INTEGER,"
            + KEY_NOMOR_MATERI + " INTEGER,"
            + KEY_ID_FILE_MATERI + " TEXT,"
            + KEY_CATATAN_MATERI + " TEXT,"
            + " FOREIGN KEY(" + KEY_ID_SUBBAB + ") REFERENCES " + TABLE_SUBBAB + "(" + KEY_ID_SUBBAB + "))";

    // PAKET_SOAL table create statement
    private static final String CREATE_TABLE_PAKET_SOAL = "CREATE TABLE " + TABLE_PAKET_SOAL
            + "(" + KEY_ID_PAKET_SOAL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ID_BAB + " INTEGER,"
            + KEY_NOMOR_PAKET_SOAL + " INTEGER,"
            + KEY_NAMA_PAKET_SOAL + " TEXT,"
            + KEY_LEVEL_SOAL + " TEXT,"
            + " FOREIGN KEY(" + KEY_ID_BAB + ") REFERENCES " + TABLE_BAB + "(" + KEY_ID_BAB + "))";

    // SOAL table create statement
    private static final String CREATE_TABLE_SOAL = "CREATE TABLE " + TABLE_SOAL
            + "(" + KEY_ID_SOAL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ID_PAKET_SOAL + " INTEGER,"
            + KEY_NOMOR_SOAL + " INTEGER,"
            + KEY_ID_FILE_SOAL + " TEXT,"
            + KEY_PILIHAN1 + " TEXT,"
            + KEY_PILIHAN2 + " TEXT,"
            + KEY_PILIHAN3 + " TEXT,"
            + KEY_PILIHAN4 + " TEXT,"
            + KEY_PILIHAN5 + " TEXT,"
            + KEY_KUNCI_SOAL + " TEXT,"
            + KEY_PEMBAHASAN_SOAL + " TEXT,"
            + KEY_CATATAN_SOAL + " TEXT,"
            + " FOREIGN KEY(" + KEY_ID_PAKET_SOAL + ") REFERENCES " + TABLE_PAKET_SOAL + "(" + KEY_ID_PAKET_SOAL + "))";

    private static DatabaseHandler instance;
    private  Context context;
    public static synchronized DatabaseHandler getHelper(Context context)
    {
        if(instance == null)
        {
            instance = new DatabaseHandler(context);
        }
        return instance;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        if(!db.isReadOnly())
        {
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_MAPEL);
        db.execSQL(CREATE_TABLE_KELAS);
        db.execSQL(CREATE_TABLE_BAB);
        db.execSQL(CREATE_TABLE_SUBBAB);
        db.execSQL(CREATE_TABLE_MATERI);
        db.execSQL(CREATE_TABLE_PAKET_SOAL);
        db.execSQL(CREATE_TABLE_SOAL);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAKET_SOAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBBAB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KELAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAPEL);
        // Create tables again
        onCreate(db);
    }

    /*
 * Creating a Bab
 */
    public long createBab(Bab bab) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_BAB, bab.getIdBab());
        values.put(KEY_ID_KELAS, bab.getIdKelas());
        values.put(KEY_ID_MAPEL, bab.getIdMapel());
        values.put(KEY_NOMOR_BAB,bab.getNomorBab());
        values.put(KEY_NAMA_BAB, bab.getNamaBab());

        // insert row
        long bab_id = db.insert(TABLE_BAB, null, values);
        return bab_id;
    }
    /*
 * getting all bab
 * */
    public List<Bab> getAllBab() {
        List<Bab> babs = new ArrayList<Bab>();
        String selectQuery = "SELECT  * FROM " + TABLE_BAB;

        Log.e("GET BABS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Bab bab = new Bab();
                bab.setIdBab(c.getInt(c.getColumnIndex(KEY_ID_BAB)));
                bab.setIdKelas(c.getInt(c.getColumnIndex(KEY_ID_KELAS)));
                bab.setIdMapel(c.getInt(c.getColumnIndex(KEY_ID_MAPEL)));
                bab.setNomorBab(c.getInt(c.getColumnIndex(KEY_NOMOR_BAB)));
                bab.setNamaBab(c.getString(c.getColumnIndex(KEY_NAMA_BAB)));

                // adding to kelas list
                babs.add(bab);
            } while (c.moveToNext());
        }

        return babs;
    }
    /*
* Creating a Kelas
*/
    public long createKelas(Kelas kelas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_KELAS, kelas.getIdKelas());
        values.put(KEY_NOMOR_KELAS, kelas.getNomorKelas());
        values.put(KEY_NAMA_KELAS, kelas.getNamaKelas());

        // insert row
        long kelas_id = db.insert(TABLE_KELAS, null, values);
        return kelas_id;
    }
    /*
* getting all kelas
* */
    public List<Kelas> getAllKelas() {
        List<Kelas> kelass = new ArrayList<Kelas>();
        String selectQuery = "SELECT  * FROM " + TABLE_KELAS;

        Log.e("GET KELASS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Kelas kelas = new Kelas();
                kelas.setIdKelas(c.getInt(c.getColumnIndex(KEY_ID_KELAS)));
                kelas.setNomorKelas(c.getInt(c.getColumnIndex(KEY_NOMOR_KELAS)));
                kelas.setNamaKelas(c.getString(c.getColumnIndex(KEY_NAMA_KELAS)));

                // adding to kelas list
                kelass.add(kelas);
            } while (c.moveToNext());
        }

        return kelass;
    }

    /*
   * getting  kelas
   * */
    public List<Kelas> getKelas(String nama_kelas) {
        List<Kelas> kelass = new ArrayList<Kelas>();
        String selectQuery = "SELECT  * FROM " + TABLE_KELAS + " WHERE " + KEY_NAMA_KELAS + "='" + nama_kelas + "'";

        Log.e("GET KELAS BY Kelas", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Kelas kelas = new Kelas();
                kelas.setIdKelas(c.getInt(c.getColumnIndex(KEY_ID_KELAS)));
                kelas.setNomorKelas(c.getInt(c.getColumnIndex(KEY_NOMOR_KELAS)));
                kelas.setNamaKelas(c.getString(c.getColumnIndex(KEY_NAMA_KELAS)));

                // adding to kelas list
                kelass.add(kelas);
            } while (c.moveToNext());
        }

        return kelass;
    }
    /*
* Creating a Mapel
*/
    public long createMapel(Mapel mapel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_MAPEL, mapel.getIdMapel());
        values.put(KEY_NAMA_MAPEL, mapel.getNamaMapel());

        // insert row
        long mapel_id = db.insert(TABLE_MAPEL, null, values);
        return mapel_id;
    }

    /*
* getting all mapel
* */
    public List<Mapel> getAllMapel() {
        List<Mapel> mapels = new ArrayList<Mapel>();
        String selectQuery = "SELECT * FROM " + TABLE_MAPEL;

        Log.e("GET MAPELS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Mapel mapel = new Mapel();
                mapel.setIdMapel(c.getInt(c.getColumnIndex(KEY_ID_MAPEL)));
                mapel.setNamaMapel(c.getString(c.getColumnIndex(KEY_NAMA_MAPEL)));

                // adding to mapel list
                mapels.add(mapel);
            } while (c.moveToNext());
        }

        return mapels;
    }
    /*
       * getting mapel by kelas
       * */
    public List<Mapel> getMapelByKelas(String kelas) {
        List<Mapel> mapels = new ArrayList<Mapel>();
        String selectQuery = "SELECT DISTINCT " + TABLE_MAPEL + "." + KEY_ID_MAPEL + "," + TABLE_MAPEL + "." + KEY_NAMA_MAPEL +
                " FROM " + TABLE_MAPEL + "," + TABLE_KELAS + ", " + TABLE_BAB +
                " WHERE " + TABLE_BAB +"."+KEY_ID_KELAS + "=" +TABLE_KELAS + "." + KEY_ID_KELAS +
                " AND " + TABLE_BAB+"."+KEY_ID_MAPEL+"="+TABLE_MAPEL+"."+ KEY_ID_MAPEL +
                " AND " + TABLE_KELAS+"."+KEY_NAMA_KELAS+"='"+kelas+"'";

        Log.e("GET MAPELBYKELAS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Mapel mapel = new Mapel();
                mapel.setIdMapel(c.getInt(c.getColumnIndex(KEY_ID_MAPEL)));
                mapel.setNamaMapel(c.getString(c.getColumnIndex(KEY_NAMA_MAPEL)));

                // adding to mapel list
                mapels.add(mapel);
            } while (c.moveToNext());
        }

        return mapels;
    }
    /*
* Creating a Materi
*/
    public long createMateri(Materi materi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_MATERI, materi.getIdMateri());
        values.put(KEY_ID_SUBBAB, materi.getIdSubbab());
        values.put(KEY_NOMOR_MATERI, materi.getNomorMateri());
        values.put(KEY_ID_FILE_MATERI, materi.getIdFileMateri());
        values.put(KEY_CATATAN_MATERI, materi.getCatatanMateri());

        // insert row
        long materi_id = db.insert(TABLE_MATERI, null, values);
        return materi_id;
    }

    /*
* getting all materi
* */
    public List<Materi> getAllMateri() {
        List<Materi> materis = new ArrayList<Materi>();
        String selectQuery = "SELECT  * FROM " + TABLE_MATERI;

        Log.e("GET MATERIS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Materi materi = new Materi();
                materi.setIdMateri(c.getInt(c.getColumnIndex(KEY_ID_MATERI)));
                materi.setIdSubbab(c.getInt(c.getColumnIndex(KEY_ID_SUBBAB)));
                materi.setNomorMateri(c.getInt(c.getColumnIndex(KEY_NOMOR_MATERI)));
                materi.setIdFileMateri(c.getString(c.getColumnIndex(KEY_ID_FILE_MATERI)));
                materi.setCatatanMateri(c.getString(c.getColumnIndex(KEY_CATATAN_MATERI)));
                // adding to materi list
                materis.add(materi);
            } while (c.moveToNext());
        }

        return materis;
    }

    /*
 * Creating a paket_soal
 */
    public long createPaketSoal(PaketSoal paketsoal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_PAKET_SOAL, paketsoal.getIdPaketSoal());
        values.put(KEY_ID_BAB, paketsoal.getIdBab());
        values.put(KEY_NOMOR_PAKET_SOAL, paketsoal.getNomorPaketSoal());
        values.put(KEY_NAMA_PAKET_SOAL, paketsoal.getNamaPaketSoal());
        values.put(KEY_LEVEL_SOAL, paketsoal.getLevelSoal());
        // insert row
        long paketsoal_id = db.insert(TABLE_BAB, null, values);
        return paketsoal_id;
    }

    /*
* getting all paket_soal
* */
    public List<PaketSoal> getAllPaketSoal() {
        List<PaketSoal> paketsoals = new ArrayList<PaketSoal>();
        String selectQuery = "SELECT  * FROM " + TABLE_PAKET_SOAL;

        Log.e("GET PAKETSOALS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                PaketSoal paketsoal = new PaketSoal();
                paketsoal.setIdPaketSoal(c.getInt(c.getColumnIndex(KEY_ID_PAKET_SOAL)));
                paketsoal.setIdBab(c.getInt(c.getColumnIndex(KEY_ID_BAB)));
                paketsoal.setNomorPaketSoal(c.getInt(c.getColumnIndex(KEY_NOMOR_PAKET_SOAL)));
                paketsoal.setNamaPaketSoal(c.getString(c.getColumnIndex(KEY_NAMA_PAKET_SOAL)));
                paketsoal.setLevelSoal(c.getString(c.getColumnIndex(KEY_LEVEL_SOAL)));
                // adding to paketsoal list
                paketsoals.add(paketsoal);
            } while (c.moveToNext());
        }

        return paketsoals;
    }
    /*
* Creating a paket_soal
*/
    public long createSoal(Soal soal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_SOAL, soal.getIdSoal());
        values.put(KEY_ID_PAKET_SOAL, soal.getIdPaketSoal());
        values.put(KEY_NOMOR_SOAL, soal.getNomorSoal());
        values.put(KEY_ID_FILE_SOAL, soal.getIdFileSoal());
        values.put(KEY_PILIHAN1, soal.getPilihan1());
        values.put(KEY_PILIHAN2, soal.getPilihan2());
        values.put(KEY_PILIHAN3, soal.getPilihan3());
        values.put(KEY_PILIHAN4, soal.getPilihan4());
        values.put(KEY_PILIHAN5, soal.getPilihan5());
        values.put(KEY_KUNCI_SOAL, soal.getKunciSoal());
        values.put(KEY_PEMBAHASAN_SOAL,soal.getPembahasanSoal());
        values.put(KEY_CATATAN_SOAL, soal.getCatatanSoal());
        // insert row
        long soal_id = db.insert(TABLE_BAB, null, values);
        return soal_id;
    }
    /*
* getting all soal
* */
    public List<Soal> getAllSoal() {
        List<Soal> soals = new ArrayList<Soal>();
        String selectQuery = "SELECT  * FROM " + TABLE_SOAL;

        Log.e("GET SOALS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Soal soal = new Soal();
                soal.setIdSoal(c.getInt(c.getColumnIndex(KEY_ID_SOAL)));
                soal.setIdPaketSoal(c.getInt(c.getColumnIndex(KEY_ID_PAKET_SOAL)));
                soal.setNomorSoal(c.getInt(c.getColumnIndex(KEY_NOMOR_SOAL)));
                soal.setIdFileSoal(c.getString(c.getColumnIndex(KEY_ID_FILE_SOAL)));
                soal.setPilihan1(c.getString(c.getColumnIndex(KEY_PILIHAN1)));
                soal.setPilihan2(c.getString(c.getColumnIndex(KEY_PILIHAN2)));
                soal.setPilihan3(c.getString(c.getColumnIndex(KEY_PILIHAN3)));
                soal.setPilihan4(c.getString(c.getColumnIndex(KEY_PILIHAN4)));
                soal.setPilihan5(c.getString(c.getColumnIndex(KEY_PILIHAN5)));
                soal.setKunciSoal(c.getString(c.getColumnIndex(KEY_KUNCI_SOAL)));
                soal.setPembahasanSoal(c.getString(c.getColumnIndex(KEY_PEMBAHASAN_SOAL)));
                soal.setCatatanSoal(c.getString(c.getColumnIndex(KEY_CATATAN_SOAL)));


                // adding to soal list
                soals.add(soal);
            } while (c.moveToNext());
        }

        return soals;
    }
    /*
* Creating a subbab
*/
    public long createSubbab(Subbab subbab) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_SUBBAB, subbab.getIdSubbab());
        values.put(KEY_ID_BAB, subbab.getIdBab());
        values.put(KEY_NOMOR_SUBBAB,subbab.getNomorSubbab());
        values.put(KEY_NAMA_SUBBAB, subbab.getNamaSubbab());

        // insert row
        long subbab_id = db.insert(TABLE_SUBBAB, null, values);
        return subbab_id;
    }
    /*
     * getting all bab
     * */
    public List<Subbab> getAllSubbab() {
        List<Subbab> subbabs = new ArrayList<Subbab>();
        String selectQuery = "SELECT  * FROM " + TABLE_SUBBAB;

        Log.e("GET SUBBABS", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Subbab subbab = new Subbab();
                subbab.setIdSubbab(c.getInt(c.getColumnIndex(KEY_ID_SUBBAB)));
                subbab.setIdBab(c.getInt(c.getColumnIndex(KEY_ID_BAB)));
                subbab.setNomorSubbab(c.getInt(c.getColumnIndex(KEY_NOMOR_SUBBAB)));
                subbab.setNamaSubbab(c.getString(c.getColumnIndex(KEY_NAMA_SUBBAB)));

                // adding to kelas list
                subbabs.add(subbab);
            } while (c.moveToNext());
        }

        return subbabs;
    }

    public List<Bab> getBab(int nomor_kelas, String nama_mapel) {
        List<Bab> babs = new ArrayList<Bab>();
        String selectQuery =
                "SELECT bab.* FROM bab, kelas, mapel WHERE " +
                "mapel.id_mapel = bab.id_mapel " +
                "AND kelas.id_kelas = bab.id_kelas " +
                "AND kelas.nomor_kelas = " + nomor_kelas +
                " AND mapel.nama_mapel = '" + nama_mapel+ "'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Bab bab = new Bab();
                bab.setIdBab(c.getInt(c.getColumnIndex(KEY_ID_BAB)));
                bab.setIdKelas(c.getInt(c.getColumnIndex(KEY_ID_KELAS)));
                bab.setIdMapel(c.getInt(c.getColumnIndex(KEY_ID_MAPEL)));
                bab.setNomorBab(c.getInt(c.getColumnIndex(KEY_NOMOR_BAB)));
                bab.setNamaBab(c.getString(c.getColumnIndex(KEY_NAMA_BAB)));

                // adding to kelas list
                babs.add(bab);
            } while (c.moveToNext());
        }

        return babs;
    }

    public List<Subbab> getSubbab(int id_bab) {
        List<Subbab> subbabs = new ArrayList<Subbab>();
        String selectQuery = "SELECT * FROM subbab WHERE subbab.id_bab = " + id_bab;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Subbab subbab = new Subbab();
                subbab.setIdSubbab(c.getInt(c.getColumnIndex(KEY_ID_SUBBAB)));
                subbab.setIdBab(c.getInt(c.getColumnIndex(KEY_ID_BAB)));
                subbab.setNomorSubbab(c.getInt(c.getColumnIndex(KEY_NOMOR_SUBBAB)));
                subbab.setNamaSubbab(c.getString(c.getColumnIndex(KEY_NAMA_SUBBAB)));

                // adding to kelas list
                subbabs.add(subbab);
            } while (c.moveToNext());
        }

        return subbabs;
    }

    public List<Materi> getMateri(int id_subbab) {
        List<Materi> materis = new ArrayList<Materi>();
        String selectQuery = "SELECT * FROM materi WHERE materi.id_subbab = " + id_subbab;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Materi materi = new Materi();
                materi.setIdMateri(c.getInt(c.getColumnIndex(KEY_ID_MATERI)));
                materi.setIdSubbab(c.getInt(c.getColumnIndex(KEY_ID_SUBBAB)));
                materi.setNomorMateri(c.getInt(c.getColumnIndex(KEY_NOMOR_MATERI)));
                materi.setIdFileMateri(c.getString(c.getColumnIndex(KEY_ID_FILE_MATERI)));
                materi.setCatatanMateri(c.getString(c.getColumnIndex(KEY_CATATAN_MATERI)));
                // adding to materi list
                materis.add(materi);
            } while (c.moveToNext());
        }

        return materis;
    }
}
