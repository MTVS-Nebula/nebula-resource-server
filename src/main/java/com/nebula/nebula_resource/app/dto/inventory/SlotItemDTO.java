package com.nebula.nebula_resource.app.dto.inventory;

import java.util.List;
import java.util.Objects;

public class SlotItemDTO {
    private String Name;
    private int Id;
    private int uniqueId;
    private List<BuffDTO> buffs;

    public SlotItemDTO() {
    }

    public SlotItemDTO(String name, int id, int uniqueId, List<BuffDTO> buffs) {
        Name = name;
        this.uniqueId = uniqueId;
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

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
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
