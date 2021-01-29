/**
 * Business logic service. <em>Service</em> is flow of <em>action</em> that
 * delivers one business functional. For example, service might have
 * <code>createInvoice(Invoice inv)</code>, in which the flow are sequence of
 * actions:
 * <ol>
 * <li>validate <code>invoice</code></li> <il>if <code>invoice</code> is not
 * valid, then terminate service</li>
 * <li>Otherwise (<code>invoice</code> is valid), calculate tax for invoice</li>
 * <li>persist <code>invoice</code> to database</li>
 * </ol>
 */
package com.course.microservice.command.service;