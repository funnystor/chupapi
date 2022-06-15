package com.diplom.e_commerce.model;

public class Computer {
    public String cCPU, cSSD, cMotherBoard, cPowerSupply, cVideoCard, cHDD, cFrame, cRAM, cCooler;

    public Computer() {
    }

    public Computer(String cCPU, String cSSD, String cMotherBoard, String cPowerSupply, String cVideoCard, String cHDD, String cFrame, String cRAM, String cCooler) {
        this.cCPU = cCPU;
        this.cSSD = cSSD;
        this.cMotherBoard = cMotherBoard;
        this.cPowerSupply = cPowerSupply;
        this.cVideoCard = cVideoCard;
        this.cHDD = cHDD;
        this.cFrame = cFrame;
        this.cRAM = cRAM;
        this.cCooler = cCooler;
    }

    public String getcCPU() {
        return cCPU;
    }

    public void setcCPU(String cCPU) {
        this.cCPU = cCPU;
    }

    public String getcSSD() {
        return cSSD;
    }

    public void setcSSD(String cSSD) {
        this.cSSD = cSSD;
    }

    public String getcMotherBoard() {
        return cMotherBoard;
    }

    public void setcMotherBoard(String cMotherBoard) {
        this.cMotherBoard = cMotherBoard;
    }

    public String getcPowerSupply() {
        return cPowerSupply;
    }

    public void setcPowerSupply(String cPowerSupply) {
        this.cPowerSupply = cPowerSupply;
    }

    public String getcVideoCard() {
        return cVideoCard;
    }

    public void setcVideoCard(String cVideoCard) {
        this.cVideoCard = cVideoCard;
    }

    public String getcHDD() {
        return cHDD;
    }

    public void setcHDD(String cHDD) {
        this.cHDD = cHDD;
    }

    public String getcFrame() {
        return cFrame;
    }

    public void setcFrame(String cFrame) {
        this.cFrame = cFrame;
    }

    public String getcRAM() {
        return cRAM;
    }

    public void setcRAM(String cRAM) {
        this.cRAM = cRAM;
    }

    public String getcCooler() {
        return cCooler;
    }

    public void setcCooler(String cCooler) {
        this.cCooler = cCooler;
    }
}
