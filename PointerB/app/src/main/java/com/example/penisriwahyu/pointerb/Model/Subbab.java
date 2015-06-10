package com.example.penisriwahyu.pointerb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krilido on 3/13/2015.
 */
public class Subbab implements Parcelable{
    int id_subbab;
    int id_bab;
    int nomor_subbab;
    String nama_subbab;

    public Subbab(){
        super();
    }

    public Subbab(int id, int bab, int no, String nama){
        super();
        this.id_subbab = id;
        this.id_bab = bab;
        this.nomor_subbab = no;
        this.nama_subbab = nama;
    }

    public Subbab(int no, String nama){
        this.nomor_subbab = no;
        this.nama_subbab = nama;
    }

    private Subbab(Parcel in){
        super();
        this.id_subbab = in.readInt();
        this.nomor_subbab = in.readInt();
        this.nama_subbab = in.readString();
    }


    public int getIdSubbab(){
        return this.id_subbab;
    }

    public void setIdSubbab(int id){
        this.id_subbab = id;
    }

    public int getIdBab(){
        return this.id_bab;
    }

    public void setIdBab(int id){
        this.id_bab = id;
    }

    public int getNomorSubbab(){
        return this.nomor_subbab;
    }

    public void setNomorSubbab(int id){
        this.nomor_subbab = id;
    }

    public String getNamaSubbab(){
        return this.nama_subbab;
    }

    public void setNamaSubbab(String nama){
        this.nama_subbab = nama;
    }

    @Override
    public String toString(){
        return "id:" + id_subbab + ", nomor:" + nomor_subbab + ", nama:" + nama_subbab;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(getIdSubbab());
        parcel.writeInt(getNomorSubbab());
        parcel.writeString(getNamaSubbab());
    }

    public static final Creator<Subbab> CREATOR = new Creator<Subbab>(){
        public  Subbab createFromParcel(Parcel in){
            return new Subbab(in);
        }

        public Subbab[] newArray(int size){
            return new Subbab[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_subbab;
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
        Subbab other = (Subbab) obj;
        if (id_subbab != other.id_subbab)
            return false;
        return true;
    }

}
