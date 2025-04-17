package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JobOption {
    private List jobList;
    private List dataList;
}
