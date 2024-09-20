package com.iiitd.registration;

import static com.iiitd.registration.Main.complaints;

public class Complaint {
    private final Student filedBy;
    private String complaint;
    private boolean resolved = false;
    private final int complaintNumber;

    public Complaint (String complaint, Student student) {
        this.complaint = complaint;
        this.filedBy = student;
        complaints.add(this);
        complaintNumber = complaints.size();
    }

    public String getComplaint() {
        return complaint;
    }

    public int getComplaintNumber() {
        return complaintNumber;
    }

    public Student getFiledBy(){
        return filedBy;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void changeStatus(){
        this.resolved = !this.resolved;
    }
}
