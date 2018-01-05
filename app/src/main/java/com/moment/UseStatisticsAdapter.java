package com.moment;

/**
 * Created by igor.rayt on 2018-01-04.
 */
/*

public class UseStatisticsAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String date;
    private final String hours;
    private final DateObjectStatistics dateObjectStatistics;

    public UseStatisticsAdapter(Context context, DateObjectStatistics dateObjectStatistics) {
        super(context, -1);
        this.context = context;
        this.dateObjectStatistics = dateObjectStatistics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList<String> statisicsConverted = new ArrayList<>();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.use_statistics_adapter, parent, false);
        statisicsConverted = converArratListToString(dateObjectStatistics);

        TextView txtDate = (TextView) rowView.findViewById(R.id.txt_date);
        TextView txtHours = (TextView) rowView.findViewById(R.id.txt_hours_used);
        txtDate.setText(dateObjectStatistics.getDate());
        txtHours.setText(dateObjectStatistics.getUseTime());

        return rowView;
    }

    private ArrayList<String> converArratListToString(DateObjectStatistics rawArayList){
        ArrayList<String> convertedList = new ArrayList<>();
        String fullString = "";
        for (int i=0; i<rawArayList.size(); ++i) {
            fullString = convertDate(rawArayList.get(i).getDate());
            fullString += ", ";
            fullString += converToTime(rawArayList.get(i).getUseTime());
            convertedList.add(fullString);
        }
        return convertedList;
    }

    private String convertDate (String rawDate){
        SimpleDateFormat dateTableFormat = new SimpleDateFormat("y-D");
        SimpleDateFormat dateFormatToDisplay = new SimpleDateFormat("E, M d");
        String dateString;
        Date bufferDate =  new Date();
        try{
            bufferDate = dateTableFormat.parse(rawDate);
        }
        catch (Exception e){
            ;
        }
        dateString = dateFormatToDisplay.format(bufferDate);

        return dateString;
    }

    private String converToTime(Long time){
        Long minutes  = TimeUnit.MILLISECONDS.toMinutes(time);
        Long hours = TimeUnit.MILLISECONDS.toHours(time);
        String timeStr = "";
        if (hours.equals(0L)){
            timeStr = Long.toString(minutes) + "m";
        }
        else{
            timeStr = Long.toString(hours) + "H " + Long.toString(minutes) + "m";
        }


        return timeStr;

}
*/
