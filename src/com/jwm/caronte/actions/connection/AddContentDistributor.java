package com.jwm.caronte.actions.connection;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.lainsoft.forge.flow.nav.CommandException;
import org.lainsoft.forge.flow.nav.GenericAction;

public class AddContentDistributor extends GenericAction {

	@Override
	public String execute() throws CommandException {
		System.out.println("server Added");		
		if(StringUtils.isNotEmpty(param("server"))){
			getServers().put(param("server"), StringUtils.defaultIfEmpty(param("port"), "16100"));
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String>
	getServers(){
		if(application("contentdistributors") == null){
			application("contentdistributors", new TreeMap<String, String>());
		}
		return (Map<String, String>) application("contentdistributors");
	}
}
