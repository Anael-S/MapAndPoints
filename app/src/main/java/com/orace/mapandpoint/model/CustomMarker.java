package com.orace.mapandpoint.model;

/**
 * This class is used to represent the custom marker who's going to be displayed on our google maps
 * You can add whatever attributes that suits you and use it to display data
 */
public class CustomMarker {
    //The logo displayed at the top of the marker's view
    String logo;
    //The description of the marker
    String description;
    //The position (long/lat) of our marker
    double latitude;
    double longitude;
    //The title displayed just below the logo
    String title;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CustomMarker() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomMarker)) {
            return false;
        }

        CustomMarker customMarker = (CustomMarker) o;

        if (Double.compare(customMarker.getLatitude(), getLatitude()) != 0) {
            return false;
        }
        if (Double.compare(customMarker.getLongitude(), getLongitude()) != 0) {
            return false;
        }
        if (getLogo() != null ? !getLogo().equals(customMarker.getLogo()) : customMarker.getLogo() != null) {
            return false;
        }
        return !(getDescription() != null ? !getDescription().equals(customMarker.getDescription()) : customMarker.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getLogo() != null ? getLogo().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
