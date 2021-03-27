package com.example.medication_reminder_android_app.UserInputHandler;

import com.example.medication_reminder_android_app.SQLiteDB.MedicationEntity;

import java.util.Date;
import java.util.Map;

/**
 * Base class for all input handlers.
 *
 * @author Samuel Jones
 * @since 3-1-2021
 */
abstract class InputHandler {
    //member variables for inputs

    InputHandler() {

    }

    abstract long inputRequest(Map<String,String> info);

    abstract void deleteRequest(String name);

    abstract void acknowledgeNotificationRequest(int reminderID, boolean dismissed);

    abstract void deleteAllRequest();

}
