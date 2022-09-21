package com.group18.activityclassification;

import androidx.annotation.NonNull;

public enum Attributes {
    RIGHT_POCKET_AX("Right_pocket_Ax"),
    RIGHT_POCKET_AY("Right_pocket_Ay"),
    RIGHT_POCKET_AZ("Right_pocket_Az"),
    RIGHT_POCKET_LX("Right_pocket_Lx"),
    RIGHT_POCKET_LY("Right_pocket_Ly"),
    RIGHT_POCKET_LZ("Right_pocket_Lz"),
    RIGHT_POCKET_GX("Right_pocket_Gx"),
    RIGHT_POCKET_GY("Right_pocket_Gy"),
    RIGHT_POCKET_GZ("Right_pocket_Gz"),
    WALKING("walking"),
    STANDING("standing"),
    JOGGING("jogging"),
    SITTING("sitting"),
    BIKING("biking"),
    UPSTAIRS("upstairs"),
    DOWNSTAIRS("downstairs");

    private final String attribute;

    Attributes(String input) {
        this.attribute = input;
    }

    @NonNull
    @Override
    public String toString() {
        return attribute;
    }
}
