package com.academia.jedi.business.Singleton;


import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class JediAcademySingleton {
    private static JediAcademySingleton instance;

    // Empty List of Jedi masters and padawans
    private List<JediMaster> jediMaster = new ArrayList<>();
    private List<Padawan> padawans = new ArrayList<>();

    public static JediAcademySingleton getInstance() {
        if (instance == null) {
            instance = new JediAcademySingleton();
        }
        return instance;
    }

}
