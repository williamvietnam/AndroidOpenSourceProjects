package com.mob.welups.data.models;

public class OnBoarding {
    private int drawableOnBoarding;
    private int titleOnBoarding;
    private int descriptionOnBoarding;

    public OnBoarding(int drawableOnBoarding, int titleOnBoarding, int descriptionOnBoarding) {
        this.drawableOnBoarding = drawableOnBoarding;
        this.titleOnBoarding = titleOnBoarding;
        this.descriptionOnBoarding = descriptionOnBoarding;
    }

    public int getDrawableOnBoarding() {
        return drawableOnBoarding;
    }

    public void setDrawableOnBoarding(int drawableOnBoarding) {
        this.drawableOnBoarding = drawableOnBoarding;
    }

    public int getTitleOnBoarding() {
        return titleOnBoarding;
    }

    public void setTitleOnBoarding(int titleOnBoarding) {
        this.titleOnBoarding = titleOnBoarding;
    }

    public int getDescriptionOnBoarding() {
        return descriptionOnBoarding;
    }

    public void setDescriptionOnBoarding(int descriptionOnBoarding) {
        this.descriptionOnBoarding = descriptionOnBoarding;
    }
}
