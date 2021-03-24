package com.example.medication_reminder_android_app.SQLiteDB;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

//doctor name
//doctor phone number

/**
@author Karley Waguespack
 @lastModified 3/22/2021 by Hayley Roberts
*/

@Entity(tableName = "DoctorTable")
public class DoctorEntity {

    public DoctorEntity(String drName, String phone, String officeLoc, String notes, String tags,
                        String officeHrs, String hospitalName, long apptID){

        this.drName = drName;
        this.phone = phone;
        this.officeLoc = officeLoc;
        this.notes = notes;
        this.tags = tags;
        this.officeHrs = officeHrs;
        this.hospitalName = hospitalName;
        this.apptID = apptID;

    }


    //need to add getter and setter methods for each attribute


    @PrimaryKey(autoGenerate = true)
    private long primaryKey;

    @ColumnInfo(name = "doctor_name")
    private String drName;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "office_location")
    private String officeLoc;

    @ColumnInfo(name = "notes")
    private String notes;

    @ColumnInfo(name = "tags")
    private String tags;

    @ColumnInfo(name = "hours")
    private String officeHrs;

    @ColumnInfo(name = "hospital_name")
    private String hospitalName;

    @ColumnInfo(name = "appointment_id")
    private long apptID;


    //Getters
    public String getDrName(){
        return this.drName;
    }

    public String getPhone() { return this.phone; }

    public String getOfficeLoc(){
        return this.officeLoc;
    }

    public String getNotes(){
        return this.notes;
    }

    public String getTags(){
        return this.tags;
    }

    public String getOfficeHrs(){
        return this.officeHrs;
    }

    public String getHospitalName(){
        return this.hospitalName;
    }

    public long getApptID() { return this.apptID; }
    
    public long getPrimaryKey(){
        return this.primaryKey;                   //Returns the primary key of the specific Doctor Information entity
    }

    //setters
    public void setPrimaryKey(long primaryKey) { this.primaryKey = primaryKey; }

    public void setDrName(String Name){
        this.drName = Name;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public void setOfficeLoc(String officeLoc){
        this.officeLoc = officeLoc;
    }

    public void setNotes(String Notes){
        this.notes = Notes;
    }

    public void setTags(String Tags){
        this.tags = Tags;
    }

    public void setOfficeHrs(String officeHrs){
        this.officeHrs = officeHrs;
    }

    public void setHospitalName(String hospitalName){
        this.hospitalName = hospitalName;
    }
    
    public void setApptID(long apptID) { this.apptID = apptID; }


}
