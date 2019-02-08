package org.Sandra.models.forms;

import org.Sandra.models.Volunteer;
import org.Sandra.models.Mass;

import javax.validation.constraints.NotNull;

public class AddMassVolunteerForm {
    private Mass mass;

    private Iterable<Volunteer> volunteers;

    @NotNull
    private int massId;

    @NotNull
    private int volunteerId;

    public AddMassVolunteerForm() { }

    public AddMassVolunteerForm(Mass mass, Iterable<Volunteer> volunteers) {
        this.mass = mass;
        this.volunteers = volunteers;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }

    public Iterable<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(Iterable<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    public int getMassId() {
        return massId;
    }

    public void setMassId(int massId) {
        this.massId = massId;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }
}
