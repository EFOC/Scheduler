package com.example.eosorio1.project; /**
 * Created by eosorio1 on 4/11/2018.
 */

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReadEvents <T>{
    private static Event event;
    public static ArrayList<Event> events = new ArrayList<>();
    ReadEvents(Context context){ readEvent(context); }

    public static void saveFile(Context context) {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("eventss.dat", context.MODE_PRIVATE));) {
            for(int i=0; i<events.size();i++) {
                Log.v("Saving Events", "SAVING THE EVENTS");
                outputStreamWriter.write(events.get(i).toString());
            }
            outputStreamWriter.close();
        } catch (IOException IO) {
            IO.printStackTrace();
        }
    }
    public static void readEvent(Context context) {
        try {
//            deleteContents(context); // If file structure was changed, delete contents in file
            events = new ArrayList<Event>();
            BufferedReader br = new BufferedReader(new InputStreamReader( context.openFileInput("eventss.dat")));
            String line = "";
            while ((line = br.readLine()) != null) {
                if(line.startsWith("ID:")){
                    int id = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                    String eventName = br.readLine();
                    String eventDesc = br.readLine();
                    LocalDateTime dateTime = LocalDateTime.parse(br.readLine());
                    LocalTime endTime = LocalTime.parse(br.readLine());
                    Event event = new Event(id, eventName, eventDesc, dateTime, endTime);
                    events.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NullPointerException e) {
            events.add(event);
            System.out.println("File is done");
        }
    }
    public static void deleteContents(Context context){ // This was used to delete all data in the file if file structure was changed
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("eventss.dat", context.MODE_PRIVATE));) {
            outputStreamWriter.write("");
        }catch (IOException e){
        }
    }

//    ALL METHODS UNDER HERE WERE WRITTEN BUT NOT IMPLEMENTED DUE TO TIME CONSTRAINT
//    // insertion sort that compares location
//    void sortbylocation() {
//        Location currLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        DataObj temp;
//        for (int i = 1; i < events.size(); i++) {
//            for(int j = i ; j > 0 ; j--){
//                if(currLocation.distanceTo(events.get(j).location) < currLocation.distanceTo(events.get(j-1).location)){
//                    temp = events.get(j);
//                    events.set(j, events.get(j-1));
//                    events.set(j-1, temp);
//                }
//            }
//        }
//
//
//    }
//    void sortbydate() {
//        DataObj temp;
//        for (int i = 1; i < events.size(); i++) {
//            for(int j = i ; j > 0 ; j--){
//                if(events.get(j).getDate().isBefore(events.get(j-1).getDate())){
//                    temp = events.get(j);
//                    events.set(j, events.get(j-1));
//                    events.set(j-1, temp);
//                }
//            }
//        }
//
//
//    }

    // checks if any end times come before their start times,
    // as well as if any start-end times overlap.
//    int isConflict() {
//        for (int i = 0; i < events.size(); i++) {
//            for (int j = i + 1; j < events.size(); j++) {
//                if (events.get(i).getDate().getDayOfYear()==events.get(j).getDate().getDayOfYear()
//                        && events.get(j).getDate().toLocalTime().isBefore(events.get(i).getEndtime())) {
//                    return 2;
//                } else if (events.get(i).getName().equals(events.get(j).getName())) {
//                    return 1;
//                } else if(events.get(i).getEndtime().isBefore(events.get(i).getDate().toLocalTime())) {
//                    return 3;
//                }
//            }
//        }
//        return 0;
//    }
}
