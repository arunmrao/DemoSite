/*
 * #%L
 * BroadleafCommerce Open Admin Platform
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.mycompany.worklow.checkout

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

import org.broadleafcommerce.common.email.service.EmailService;
import org.broadleafcommerce.common.email.service.info.EmailInfo;
import org.broadleafcommerce.core.checkout.service.workflow.CheckoutSeed;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderImpl;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.DefaultProcessContextImpl;
import org.broadleafcommerce.core.workflow.ProcessContext;
import org.broadleafcommerce.openadmin.dto.BasicFieldMetadata
import org.broadleafcommerce.openadmin.server.service.persistence.validation.RequiredPropertyValidator
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.domain.CustomerImpl;

import spock.lang.Specification

import org.springframework.context.ApplicationContext

/**
 * 
 * @author Arun Rao @arunmrao
 */
class SendOrderConfirmationEmailActivitySpec extends Specification {
    
	BaseActivity<ProcessContext<CheckoutSeed>> activity
    ProcessContext<CheckoutSeed> context
    EmailService theEmailService = Mock()

    def setup() {
        Order order = new OrderImpl()
        
        context = new DefaultProcessContextImpl<CheckoutSeed>().with {
			seedData = new CheckoutSeed(order, null)
			it
		}
        
        activity = new SendOrderConfirmationEmailActivity().with {
    		emailService = theEmailService
            it
        }
    }
    
    def "Test that email is sent successfully"() {
    	when: "I execute the SendOrderConfirmationEmailActivity"
		context = activity.execute(context)
    
    	then: "An email is sent"
    	1 * theEmailService.sendTemplateEmail(_, _, _)
    }
}
