package com.group18.activityclassification;

import java.util.LinkedList;

public class Queue {

    java.util.Queue<Attributes> queue;
    final int queueLength = 5;

    public Queue() {
        this.queue = new LinkedList<>();

        for(int i = 0; i < queueLength; i++){
            queue.add(null);
        }
    }

    public void addQueue(Attributes activity){
        queue.remove();
        queue.add(activity);
    }

    public Attributes tallyQueue(){
        int[] tallyArray = {0, 0, 0, 0, 0, 0, 0};
        java.util.Queue<Attributes> tallyQueue = new LinkedList<>(queue);

        for(int i = 0; i < queueLength; i++){
            Attributes type = tallyQueue.poll();
            switch(type){
                case WALKING:
                    tallyArray[0]++;
                    break;
                case STANDING:
                    tallyArray[1]++;
                    break;
                case JOGGING:
                    tallyArray[2]++;
                    break;
                case SITTING:
                    tallyArray[3]++;
                    break;
                case BIKING:
                    tallyArray[4]++;
                    break;
                case UPSTAIRS:
                    tallyArray[5]++;
                    break;
                case DOWNSTAIRS:
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

        switch(indexMax){
            case 0:
                return Attributes.WALKING;
            case 1:
                return Attributes.STANDING;
            case 2:
                return Attributes.JOGGING;
            case 3:
                return Attributes.SITTING;
            case 4:
                return Attributes.BIKING;
            case 5:
                return Attributes.UPSTAIRS;
            case 6:
                return Attributes.DOWNSTAIRS;
            default:
                return null;
        }
    }
}
