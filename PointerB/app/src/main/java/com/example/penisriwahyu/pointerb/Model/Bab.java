package com.example.penisriwahyu.pointerb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krilido on 3/13/2015.
 */
public class Bab implements Parcelable{

    int id_bab;
    int id_kelas;
    int id_mapel;
    int nomor_bab;
    String nama_bab;

    public Bab(){
        super();
    }

    public Bab(int id, int kel, int mapel,int no, String nama){
        super();
        this.id_mapel = mapel;
        this.id_bab = id;
        this.id_kelas = kel;
        this.nomor_bab = no;
        this.nama_bab = nama;
    }

    public Bab(int no, String nama){
        this.nomor_bab = no;
        this.nama_bab = nama;
    }

    private Bab(Parcel in){
        this.id_bab = in.readInt();
        this.nomor_bab = in.readInt();
        this.nama_bab = in.readString();
    }

    public int getIdBab(){
        return this.id_bab;
    }

    public void setIdBab(int id){
        this.id_bab = id;
    }

    public int getIdKelas(){
        return this.id_kelas;
    }

    public void setIdKelas(int id){
        this.id_kelas = id;
    }

    public int getIdMapel(){
        return this.id_mapel;
    }

    public void setIdMapel(int id){
        this.id_mapel = id;
    }

    public int getNomorBab(){
        return this.nomor_bab;
    }

    public void setNomorBab(int id){
        this.nomor_bab = id;
    }

    public String getNamaBab(){
        return this.nama_bab;
    }

    public void setNamaBab(String nama){
        this.nama_bab = nama;
    }

    @Override
    public String toString(){
        return "id:" + id_bab + ", nomor:" + nomor_bab + ", Nama:" + nama_bab;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(getIdBab());
        parcel.writeInt(getNomorBab());
        parcel.writeString(getNamaBab());
     }

    public static final Creator<Bab> CREATOR = new Creator<Bab>(){
        public  Bab createFromParcel(Parcel in){
            return new Bab(in);
        }

        public Bab[] newArray(int size){
            return new Bab[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_bab;
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
        Bab other = (Bab) obj;
        if (id_bab != other.id_bab)
            return false;
        return true;
    }
}

