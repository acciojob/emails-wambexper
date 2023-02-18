package com.driver;

//import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class EndSorter implements Comparator<Meeting>
{
    @Override
    //Sorting meetings based on finish timings
    public int compare(Meeting o1, Meeting o2)
    {
        //o1.start < o2.end
        if (o1.getEndTime().isBefore(o2.getEndTime()))
            return -1;

            //o1.end > o2.end
        else if (o1.getEndTime().isAfter(o2.getEndTime()))
            return 1;

        return 0;
    }
}
public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar=new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int c = 1;

        EndSorter es = new EndSorter();

        // Sorting of meeting according to their finish time.
        Collections.sort(calendar, es);

        // First meeting is always selected
        // m.add(calendar.get(0).pos);

        LocalTime prev_end = calendar.get(0).getEndTime();

        // Checking if meetings can take place or not.
        for(int i = 1; i < calendar.size(); i++)
        {
            if (calendar.get(i).getStartTime().isAfter(prev_end))
            {
                //m.add(meet.get(i).pos);
                prev_end = calendar.get(i).getEndTime();
                c++;
            }
        }

        return c;
    }
}