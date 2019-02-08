package org.launchcode.models;

        import javax.persistence.*;
        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Size;
        import java.util.ArrayList;
        import java.util.List;

@Entity
public class Ministry {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn(name="ministry_id")
    private List<Volunteer> volunteers = new ArrayList<>();

    public Ministry() { }

    public Ministry(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
    public void setName(String name) {
        this.name = name;
    }
}
