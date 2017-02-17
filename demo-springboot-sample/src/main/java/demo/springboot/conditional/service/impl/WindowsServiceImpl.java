package demo.springboot.conditional.service.impl;

import demo.springboot.conditional.service.ListService;


public class WindowsServiceImpl implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
