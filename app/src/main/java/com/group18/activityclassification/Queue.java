package com.group18.activityclassification;

import java.util.LinkedList;

public class Queue {

    java.util.Queue<String> queue;
    final int queueLength = 5;

    public Queue() {
        this.queue = new LinkedList<String>();

        for(int i = 0; i < queueLength; i++){
            queue.add("0/");
        }
    }

    public void addQueue(String activity){
        queue.remove();
        queue.add(activity);
    }

    public String tallyQueue(){
        int[] tallyArray = {0, 0, 0, 0, 0, 0, 0};
        java.util.Queue<String> tallyQueue = queue;
        for(int i = 0; i < queueLength; i++){
            String type = tallyQueue.poll();
            switch(type){
                case "walking":
                    tallyArray[0]++;
                    break;
                case "standing":
                    tallyArray[1]++;
                    break;
                case "jogging":
                    tallyArray[2]++;
                    break;
                case "sitting":
                    tallyArray[3]++;
                    break;
                case "biking":
                    tallyArray[4]++;
                    break;
                case "upstairs":
                    tallyArray[5]++;
                    break;
                case "downstairs":
                    tallyArray[6]++;
                    break;
                default:
                    break;
            }
        }

        int indexMax = 0;
        for(int i = 0; i < 7; i++){
            indexMax = tallyArray[i] > tallyArray[indexMax] ? i : indexMax;
        }

        String activity = "\0";
        switch(indexMax){
            case 0:
                activity = "walking";
                break;
            case 1:
                activity = "standing";
                break;
            case 2:
                activity = "jogging";
                break;
            case 3:
                activity = "sitting";
                break;
            case 4:
                activity = "biking";
                break;
            case 5:
                activity = "upstairs";
                break;
            case 6:
                activity = "downstairs";
                break;
            default:
                break;
        }

        return activity;
    }
}
