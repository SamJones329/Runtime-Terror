package com.example.medication_reminder_android_app.SQLiteDB;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @author Hayley Roberts
 * @lastModified 3/5/2021 by Hayley Roberts
 */

public class MainViewModel extends AndroidViewModel {
    private final DatabaseRepository repository;
    private final MutableLiveData<MedicationEntity[]> meds;
    private final MutableLiveData<ReminderEntity[]> reminders;

    public MainViewModel(Application application){
        super(application);
        repository = new DatabaseRepository(application);
        meds = repository.filterMedications(new String[] {""});
        reminders = repository.getReminders(5);
    }


    //Methods to be used in other places in the code like the UI and notification
    public MutableLiveData<MedicationEntity[]> getMeds(String[] tags){
        return repository.filterMedications(tags);
    }

    public MutableLiveData<MedicationEntity[]> getMeds(){
        return repository.filterMedications(new String[] {""});
    }

    public MutableLiveData<ReminderEntity[]> getReminders(int numOfReminders){
        return repository.getReminders(numOfReminders);
    }

    public MutableLiveData<String[]> getAllMedNames(){
        MutableLiveData<String[]> retVal = new MutableLiveData<>();
        MedicationEntity[] meds = getMeds().getValue();
        String[] medNames = new String[meds.length];

        for(int i = 0; i < meds.length; i++){
            medNames[i] = meds[i].getMedName();
        }

        retVal.setValue(medNames);
        return retVal;
    }

    //methods to insert rows into tables
    //TODO Reminder Id handling
    //Maybe have all insertion return the primary keys
    //For timing purposes maybe i have this method creating a reminder row inside of it.
    public Integer insertMedication(String medicationName, String inputDosage, boolean ifRecurring, String firstDate,
                                 String inputTimeRule, String inputWarnings, String inputIngredients, String inputTags){
        Integer recurringBool = ifRecurring? 1 : 0;
        MedicationEntity medication = new MedicationEntity(medicationName, inputDosage, recurringBool, firstDate,
                inputTimeRule, 0, "", inputWarnings, inputIngredients, inputTags);
        repository.insertMed(medication);
        return medication.getPrimaryKey();
    }

    public void insertMedAndReminder(){
        //TODO insert medication and reminder
    }

    public void updateAcknowledgements(MedicationEntity m, String newAcknowedgementList){
        repository.updateAcknowledgements(m, newAcknowedgementList);
    }

    public void updateAcknowledgements(String medName, String newAcknowledgementList){
        MedicationEntity m = repository.getMedByName(medName);
        repository.updateAcknowledgements(m, newAcknowledgementList);
    }

    public void updateAcknowledgements(Integer MedPrimaryKey, String newAcknowledgementList){
        MedicationEntity m = repository.getMedById(MedPrimaryKey);
        repository.updateAcknowledgements(m, newAcknowledgementList);
    }

    public Integer insertReminder(String classification, String time, String date, Integer timeIntervalIndex, Integer medApptId){
        ReminderEntity reminder = new ReminderEntity(classification, time, date, timeIntervalIndex, medApptId);
        repository.insertReminder(reminder);
        return reminder.getPrimaryKey();
    }


    //methods to delete rows from tables
    public void deleteReminder(MedicationEntity medEntity){
        ReminderEntity r = repository.getReminderById(medEntity.getReminderID());
        repository.deleteReminder(r);
    }

    public void deleteReminder(AppointmentEntity apptEntity){
        ReminderEntity r = repository.getReminderById(apptEntity.getRemindTabID());
        repository.deleteReminder(r);
    }

    public void deleteReminder(ReminderEntity r){
        repository.deleteReminder(r);
    }

    public void deleteMedication(MedicationEntity m){
        repository.deleteMed(m);
    }

    public void deleteMedication(String medName){
        MedicationEntity m = repository.getMedByName(medName);
        Integer medRid = m.getReminderID();
        repository.deleteReminder(repository.getReminderById(medRid));
        repository.deleteMed(m);
    }


    //methods to clear tables of all values
    public void deleteAllMedications(){
        repository.deleteAllMeds();
        repository.deleteAllMedReminders();
    }

    public void deleteAllReminders(){
        repository.deleteAllReminders();
    }


}
