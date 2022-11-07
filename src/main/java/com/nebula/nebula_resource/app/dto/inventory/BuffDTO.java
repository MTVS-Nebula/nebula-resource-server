package com.nebula.nebula_resource.app.dto.inventory;

public class BuffDTO {
    private int stat;
    private int value;
    private int min;
    private int max;

    public BuffDTO() {
    }

    public BuffDTO(int stat, int value, int min, int max) {
        this.stat = stat;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "BuffDTO{" +
                "stat=" + stat +
                ", value=" + value +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
