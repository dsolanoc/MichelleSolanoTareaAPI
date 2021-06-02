package com.example.tareaapi.models;

public class Journal {
    private String issue_id;
    private String volume;
    private String number;
    private String year;
    private String date_published;
    private String title;
    private String doi;
    private String cover;

    @Override
    public String toString() {
        return "{" +
                "issue_id:" + issue_id + ",\n" +
                " volume:" + volume + ",\n" +
                " number:" + number + ",\n" +
                " year:" + year + ",\n" +
                " date_published:" + date_published + ",\n" +
                " title:" + title + ",\n" +
                " doi:" + doi + ",\n" +
                " cover:" + cover + ",\n" +
                "}\n_______________________________________\n\n";
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
