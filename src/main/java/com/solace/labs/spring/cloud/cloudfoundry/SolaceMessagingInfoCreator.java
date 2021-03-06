/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.solace.labs.spring.cloud.cloudfoundry;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import com.solace.labs.spring.cloud.core.SolaceMessagingInfo;

public class SolaceMessagingInfoCreator extends CloudFoundryServiceInfoCreator<SolaceMessagingInfo> {

	// This creator will accept and parse any credentials that have the matching tag or label. 
	// Therefore the default accept method is sufficient and doesn't need further specification.
	static private String solaceMessagingTag = "solace-messaging";

	public SolaceMessagingInfoCreator() {
		super(new Tags(solaceMessagingTag));
	}

	@SuppressWarnings("unchecked")
	@Override
	public SolaceMessagingInfo createServiceInfo(Map<String, Object> serviceData) {
		String id = getId(serviceData);

		String clientUsername = null;
		String clientPassword = null;
		String msgVpnName = null;
		String smfHost = null;
		String smfTlsHost = null;
		String smfZipHost = null;
		String webMessagingUri = null;
		String jmsJndiUri = null;
		String jmsJndiTlsUri = null;
		List<String> restUris = null;
		List<String> restTlsUris = null;
		List<String> mqttUris = null;
		List<String> mqttTlsUris = null;
		List<String> mqttWsUris = null;
		List<String> mqttWssUris = null;
		List<String> managementHttpUris = null;
		List<String> managementHttpsUris = null;
		String managementPassword = null;
		String managementUsername = null;

		Map<String, Object> credentials = getCredentials(serviceData);

		if (credentials == null) {
			throw new IllegalArgumentException("Received null credentials during object creation");
		}

		// Populate this the quick and dirty way for now. Can improve later as
		// we harden. As a start, we'll be tolerant of missing attributes and
		// just leave fields set to null.
		for (Map.Entry<String, Object> entry : credentials.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			switch (key) {
			case "clientUsername":
				clientUsername = (String) value;
				break;
			case "clientPassword":
				clientPassword = (String) value;
				break;
			case "msgVpnName":
				msgVpnName = (String) value;
				break;
			case "smfHost":
				smfHost = (String) value;
				break;
			case "smfTlsHost":
				smfTlsHost = (String) value;
				break;
			case "smfZipHost":
				smfZipHost = (String) value;
				break;
			case "webMessagingUri":
				webMessagingUri = (String) value;
				break;
			case "jmsJndiUri":
				jmsJndiUri = (String) value;
				break;
			case "jmsJndiTlsUri":
				jmsJndiTlsUri = (String) value;
				break;
			case "managementUsername":
				managementUsername = (String) value;
				break;
			case "managementPassword":
				managementPassword = (String) value;
				break;
			case "restUris":
				restUris = (List<String>) value;
				break;
			case "restTlsUris":
				restTlsUris = (List<String>) value;
				break;
			case "mqttUris":
				mqttUris = (List<String>) value;
				break;
			case "mqttTlsUris":
				mqttTlsUris = (List<String>) value;
				break;
			case "mqttWsUris":
				mqttWsUris = (List<String>) value;
				break;
			case "mqttWssUris":
				mqttWssUris = (List<String>) value;
				break;
			case "managementHttpUris":
				managementHttpUris = (List<String>) value;
				break;
			case "managementHttpsUris":
				managementHttpsUris = (List<String>) value;
				break;
			}
		}

		SolaceMessagingInfo solMessagingInfo = new SolaceMessagingInfo(id, clientUsername, clientPassword, msgVpnName,
				smfHost, smfTlsHost, smfZipHost, webMessagingUri, jmsJndiUri, jmsJndiTlsUri, restUris, restTlsUris, mqttUris,
				mqttTlsUris, mqttWsUris, mqttWssUris, managementHttpUris, managementHttpsUris, managementPassword,
				managementUsername);

		return solMessagingInfo;
	}
}
