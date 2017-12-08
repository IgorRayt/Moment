package com.moment;

/**
 * Created by igor.rayt on 2017-11-01.
 * Hope it will help :) Good luck!
 */

public class LocalDraft {
    //array could be any length
    private Integer [] arrayToSort = new Integer[4];

    public void sortArray(){
        Integer bufferValue = 0;
        Integer nextPlace = 0;
        //array can take any int values
        arrayToSort[0] = 1;
        arrayToSort[1] = 2;
        arrayToSort[2] = 2;
        arrayToSort[3] = 4;

        for (int i = 0; i< arrayToSort.length; i++){
            nextPlace = i + 1;

            if (nextPlace >= arrayToSort.length){
                break;
            }

            while (!compareValues(arrayToSort[i], arrayToSort[nextPlace])){
                bufferValue = arrayToSort[i];
                arrayToSort[i] = arrayToSort[nextPlace];
                arrayToSort[nextPlace] = bufferValue;
                nextPlace++;

                if (nextPlace >= arrayToSort.length){
                    break;

                }
            }
        }
    }

    private Boolean compareValues(int firstValue, int secondValue){
        if (firstValue > secondValue){
            return true;
        }
        return false;
    }

    private void bullShit(){

        for (int i = 0; i < arrayToSort.length; i++) {

        }
    }
}
