package edu.hust.it4409.booking.hotel.room.amenity.bed;

public record OtherBedType(int numberOfAdults, String recognizableName, String description, double width, double heigth)
    implements BedType {
    
    public OtherBedType {
        recognizableName = recognizableName.toUpperCase();
        description = description != null ? description : "No description found";
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public double getWidth() {
        return width;
    }
    
    @Override
    public double getHeight() {
        return heigth;
    }
    
}
