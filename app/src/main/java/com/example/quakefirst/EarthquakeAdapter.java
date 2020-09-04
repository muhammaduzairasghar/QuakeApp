package com.example.quakefirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
//Constructs a new Earthquake Adapter

    //Cotext of the app
    //earthquakes is the listof the earthquake,Which is the data source of adapter

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if there is an existing listview(Called convertview) that can reuse,
        //Otherwise, if convertview is null then imflate a new  list item layout.
        View ListItemView = convertView;
        if (ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);

        }
        //Find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        String originalLocation = currentEarthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        //Find the text view with view ID magnitude
        TextView magnitudeView = (TextView) ListItemView.findViewById(R.id.magnitude);
        //Dipla the magnitude of th3e current earthquake in the textview
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);
// Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        TextView primaryLocationView = (TextView) ListItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) ListItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) ListItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) ListItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        return  ListItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /*
           // Create a new Date object from the time in milliseconds of the earthquake
           Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

           // Find the TextView with view ID date
           TextView dateView = (TextView) ListItemView.findViewById(R.id.date);
           // Format the date string (i.e. "Mar 3, 1984")
           String formattedDate = formatDate(dateObject);
           // Display the date of the current earthquake in that TextView
           dateView.setText(formattedDate);

           // Find the TextView with view ID time
           TextView timeView = (TextView) ListItemView.findViewById(R.id.time);
           // Format the time string (i.e. "4:30PM")
           String formattedTime = formatTime(dateObject);
           // Display the time of the current earthquake in that TextView
           timeView.setText(formattedTime);


           return ListItemView;


       }
       /**
        * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
        */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
