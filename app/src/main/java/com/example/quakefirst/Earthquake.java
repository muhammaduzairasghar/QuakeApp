package com.example.quakefirst;

public class Earthquake {

    //Magnitude of the earthquake
    private double mMagnitude;

    //Location of the earthquake
    private String mLocation;

    //Date of the earthquake
    private long mTimeInMilliseconds;

    private String mUrl;

    // Construct a new object.
    //magnitude id the magntude (size of the earthquake)
    //location is the city of the eathquake
    //date id the date of the earthquake happened

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {

        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;

    }

    //Return the magnitude of the earthquake

    public double getMagnitude() {
        return mMagnitude;
    }

    //Return the location of the earthquake

    public String getLocation() {
        return mLocation;
    }

    //Return the date of earthquake

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;

    }
}