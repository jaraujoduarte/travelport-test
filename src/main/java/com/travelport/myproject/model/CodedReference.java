package com.travelport.myproject.model;

import java.util.List;

public class CodedReference {

    /**
     * Positions for the attributes below
     */
    protected int idPos = 0;
    protected int namePos = 1;

    /**
     * All coded reference are supposed to have at least these
     */
    private String id;
    private String name;

    public CodedReference() {
    }

    public CodedReference(List<String> line) {
        overrideVars();
        buildObject(line);
    }

    /**
     * Method to populate at least the attributes defined as default
     * 
     * @param line
     *            Line on the file from which the codes are being read from
     */
    public void buildObject(List<String> line) {
        this.id = line.get(idPos);
        this.name = line.get(namePos);
        buildObjectCustom(line);
    }

    /**
     * Method for the different model objects to override the values of the
     * attributes before building the object
     * 
     */
    protected void overrideVars() {
    }

    /**
     * Method for the different model objects to extend the list of attributes
     * 
     * @param line
     *            Line on the file from which the codes are being read from
     */
    protected void buildObjectCustom(List<String> line) {
    }

    public int getIdPos() {
        return idPos;
    }

    public void setIdPos(int idPos) {
        this.idPos = idPos;
    }

    public int getNamePos() {
        return namePos;
    }

    public void setNamePos(int namePos) {
        this.namePos = namePos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
