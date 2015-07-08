package wa.filipe.finalproject.module;
import java.io.Serializable;

public class Build implements Serializable {
    private Integer slots, rank, catalyst, id;
    private String mods;

    public Build(){}

    public Build(Integer slots, Integer rank, Integer catalyst, String mods){
        this.slots = slots;
        this.rank = rank;
        this.catalyst = catalyst;
        this.mods = mods;
    }

    @Override
    public String toString() {
        return "Build{" +
                "slots=" + slots +
                ", rank=" + rank +
                ", catalyst=" + catalyst +
                ", mods=" + mods +
                '}';
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(Integer catalyst) {
        this.catalyst = catalyst;
    }

    public String getMods() {
        return mods;
    }

    public void setMods(String mods) {
        this.mods = mods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
