package com.example.platform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private Long total;//总条数
    private List<T> items;//内容

    public void setTotal(long total) {
        this.total=total;
    }

    public void setItems(List<T> items) {
        this.items=items;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }
}
