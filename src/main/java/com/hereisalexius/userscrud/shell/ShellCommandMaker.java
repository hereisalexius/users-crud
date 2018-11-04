package com.hereisalexius.userscrud.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.hereisalexius.userscrud.UsersCrudApplication;


@ShellComponent
public class ShellCommandMaker{
	
    @ShellMethod("Start users-crud web-applicaton.")
	public String start() {
		return "started";
	}
   
    

}
