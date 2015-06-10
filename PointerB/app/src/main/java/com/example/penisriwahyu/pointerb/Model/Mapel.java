package com.example.penisriwahyu.pointerb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krilido on 3/13/2015.
 */
public class Mapel implements Parcelable {

    int id_mapel;
    String nama_mapel;

    public Mapel(){
        super();
    }

    public Mapel (int id, String nama){
        super();
        this.id_mapel = id;
        this.nama_mapel = nama;

    }

    public Mapel(String nama){
        this.nama_mapel = nama;
    }

    private Mapel(Parcel in){
        super();
        this.id_mapel = in.readInt();
        this.nama_mapel = in.readString();
    }

    public int getIdMapel(){
        return this.id_mapel;
    }

    public void setIdMapel(int id){
        this.id_mapel = id;
    }

    public String getNamaMapel(){
        return this.nama_mapel;
    }

    public void setNamaMapel(String nama){
        this.nama_mapel = nama;
    }

    @Override
    public String toString(){
        return "id:" + id_mapel + ", name:" +nama_mapel;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(getIdMapel());
        parcel.writeString(getNamaMapel());
    }

    public static final Creator<Mapel> CREATOR = new Creator<Mapel>(){
        public  Mapel createFromParcel(Parcel in){
            return  new Mapel(in);
        }

        public Mapel[] newArray(int size){
            return new Mapel[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_mapel;
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
        Mapel other = (Mapel) obj;
        if (id_mapel != other.id_mapel)
            return false;
        return true;
    }

}
