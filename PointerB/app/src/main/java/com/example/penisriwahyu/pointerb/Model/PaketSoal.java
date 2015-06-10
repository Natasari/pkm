package com.example.penisriwahyu.pointerb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krilido on 3/13/2015.
 */
public class PaketSoal implements Parcelable{

    int id_paket_soal;
    int id_bab;
    int nomor_paket_soal;
    String nama_paket_soal;
    String level_soal;

    public PaketSoal(){
        super();
    }

    public  PaketSoal(int id, int bab, int no, String nama, String level){
        super();
        this.id_paket_soal = id;
        this.id_bab = bab;
        this.nomor_paket_soal = no;
        this.nama_paket_soal = nama;
        this.level_soal = level;
    }

    public PaketSoal(int no, String nama, String level){
        this.nomor_paket_soal = no;
        this.nama_paket_soal = nama;
        this.level_soal = level;
    }

    private PaketSoal(Parcel in){
        super();
        this.id_paket_soal = in.readInt();
        this.nomor_paket_soal = in.readInt();
        this.nama_paket_soal = in.readString();
        this.level_soal = in.readString();
    }

    public int getIdBab(){
        return this.id_bab;
    }

    public void setIdBab(int id){
        this.id_bab = id;
    }

    public int getIdPaketSoal(){
        return this.id_paket_soal;
    }

    public void setIdPaketSoal(int id){
        this.id_paket_soal = id;
    }

    public int getNomorPaketSoal(){
        return this.nomor_paket_soal;
    }

    public void setNomorPaketSoal(int id){
        this.nomor_paket_soal = id;
    }

    public String getNamaPaketSoal(){
        return this.nama_paket_soal;
    }

    public void setNamaPaketSoal(String nama){
        this.nama_paket_soal = nama;
    }

    public String getLevelSoal(){
        return this.level_soal;
    }

    public void setLevelSoal(String level){
        this.level_soal = level;
    }

    @Override
    public String toString(){
        return "id:" + id_paket_soal + ", nomor:" + nomor_paket_soal + ", Nama:" + nama_paket_soal + ", Level:" + level_soal;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(getIdPaketSoal());
        parcel.writeInt(getNomorPaketSoal());
        parcel.writeString(getNamaPaketSoal());
        parcel.writeString(getLevelSoal());
    }

    public static final Creator<PaketSoal> CREATOR = new Creator<PaketSoal>(){
        public  PaketSoal createFromParcel(Parcel in){
            return new PaketSoal(in);
        }

        public PaketSoal[] newArray(int size){
            return new PaketSoal[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_paket_soal;
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaketSoal other = (PaketSoal) obj;
        if (id_paket_soal != other.id_paket_soal)
            return false;
        return true;
    }
}
