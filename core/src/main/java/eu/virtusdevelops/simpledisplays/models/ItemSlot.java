package eu.virtusdevelops.simpledisplays.models;

public enum ItemSlot {
    NONE(0),
    THIRD_PERSON_LEFT_HAND(1),
    THIRD_PERSON_RIGHT_HAND(2),
    FIRST_PERSON_LEFT_HAND(3),
    FIRST_PERSON_RIGHT_HAND(4),
    HEAD(5),
    GUI(6),
    GROUND(7),
    FIXED(9);

    final int id;
    ItemSlot(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
