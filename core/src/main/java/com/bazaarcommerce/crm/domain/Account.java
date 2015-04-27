package com.bazaarcommerce.crm.domain;

import java.util.Set;

import org.broadleafcommerce.profile.core.domain.Customer;

public class Account {
	protected String name;
	
	protected Organization organization; //belongsTo
	
	protected Customer manager;
	protected Set<Customer> customers; //hasMany
	
	protected Contract defaultContract;
	protected Set<Contract> contracts;
}
