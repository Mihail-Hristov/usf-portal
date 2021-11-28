package com.example.backend.service;

import com.example.backend.model.entity.GroupName;
import com.example.backend.model.entity.enumeration.GroupNameEnum;

public interface GroupNameService {

    public void initGroups();

    public GroupName findGroupByName(GroupNameEnum name);

    public GroupName findGroupByName(String name);
}
