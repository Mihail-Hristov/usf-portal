package com.example.backend.service;

import com.example.backend.models.entity.GroupName;
import com.example.backend.models.entity.enumeration.GroupNameEnum;

public interface GroupNameService {

    public void initGroups();

    public GroupName findGroupByName(GroupNameEnum name);

    public GroupName findGroupByName(String name);
}
