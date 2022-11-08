package com.nebula.nebula_resource.app.dto.inventory;

import java.util.List;

public class SlotItemDTO {
    private String Name;
    private int Id;
    private List<BuffDTO> buffs;

    public SlotItemDTO() {
    }

    public SlotItemDTO(String name, int id, List<BuffDTO> buffs) {
        Name = name;
        Id = id;
        this.buffs = buffs;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<BuffDTO> getBuffs() {
        return buffs;
    }

    public void setBuffs(List<BuffDTO> buffs) {
        this.buffs = buffs;
    }

    @Override
    public String toString() {
        return "SlotItemDTO{" +
                "Name='" + Name + '\'' +
                ", Id=" + Id +
                ", buffs=" + buffs +
                '}';
    }
}
