package eu.virtusdevelops.simpledisplays.models;

public enum EntityBillboard {
    FIXED(0),
    /**
     * Can pivot around vertical axis.
     */
    VERTICAL(2),
    /**
     * Can pivot around horizontal axis.
     */
    HORIZONTAL(1),
    /**
     * Can pivot around center point.
     */
    CENTER(3);

    private final int id;
    EntityBillboard(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
