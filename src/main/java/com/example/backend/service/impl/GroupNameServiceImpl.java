package com.example.backend.service.impl;

import com.example.backend.model.entity.GroupName;
import com.example.backend.model.entity.enumeration.GroupNameEnum;
import com.example.backend.repository.GroupNameRepository;
import com.example.backend.service.GroupNameService;
import org.springframework.stereotype.Service;

@Service
public class GroupNameServiceImpl implements GroupNameService {

    private final GroupNameRepository groupNameRepository;

    public GroupNameServiceImpl(GroupNameRepository groupNameRepository) {
        this.groupNameRepository = groupNameRepository;
    }

    @Override
    public void initGroups() {

        if (groupNameRepository.count() != 0) {
            return;
        }

        for (GroupNameEnum groupNameEnum : GroupNameEnum.values()) {
            GroupName groupName = new GroupName();
            groupName.setName(groupNameEnum);
            groupNameRepository.save(groupName);
        }
    }

    @Override
    public GroupName findGroupByName(GroupNameEnum name) {
        return groupNameRepository.findAllByName(name);
    }

    @Override
    public GroupName findGroupByName(String name) {
        return groupNameRepository.findAllByName(GroupNameEnum.valueOf(name));
    }
}
