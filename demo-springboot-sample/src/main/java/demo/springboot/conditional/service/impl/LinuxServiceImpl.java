package demo.springboot.conditional.service.impl;

import demo.springboot.conditional.service.ListService;

public class LinuxServiceImpl implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
