/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.apiman.gateway.platforms.war.micro;

import io.apiman.gateway.api.rest.impl.IPlatform;
import io.apiman.gateway.engine.beans.ApiEndpoint;
import io.apiman.gateway.engine.beans.GatewayEndpoint;
import io.apiman.gateway.platforms.war.WarGateway;
import io.apiman.gateway.platforms.war.filters.HttpRequestThreadLocalFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * The microservice platform integration impl.
 *
 * @author eric.wittmann@redhat.com
 */
public class GatewayMicroServicePlatform implements IPlatform {

    public static final String APIMAN_GATEWAY_ENDPOINT = "apiman.micro.gateway.public-endpoint"; //$NON-NLS-1$

    /**
     * Constructor.
     */
    public GatewayMicroServicePlatform() {
    }

    /**
     * @see io.apiman.gateway.api.rest.impl.IPlatform#getApiEndpoint(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public ApiEndpoint getApiEndpoint(String organizationId, String apiId, String version) {
        StringBuilder builder = getGatewayEndpoint();
        builder.append(organizationId);
        builder.append("/"); //$NON-NLS-1$
        builder.append(apiId);
        builder.append("/"); //$NON-NLS-1$
        builder.append(version);

        ApiEndpoint rval = new ApiEndpoint();
        rval.setEndpoint(builder.toString());
        return rval;
    }

    /**
     * @see io.apiman.gateway.api.rest.impl.IPlatform#getEndpoint()
     */
    @Override
    public GatewayEndpoint getEndpoint() {
        GatewayEndpoint gatewayEndpoint = new GatewayEndpoint();
        gatewayEndpoint.setEndpoint(getGatewayEndpoint().toString());
        return gatewayEndpoint;
    }

    /**
     * Determine gateway endpoint
     * @return gateway endpoint as StringBuilder
     */
    private StringBuilder getGatewayEndpoint() {
        String endpoint = WarGateway.config.getConfigProperty(APIMAN_GATEWAY_ENDPOINT, null);
        StringBuilder builder = new StringBuilder();
        if (endpoint == null) {
            HttpServletRequest currentRequest = HttpRequestThreadLocalFilter.getCurrentRequest();
            if (currentRequest == null) {
                throw new RuntimeException("No current request available.  Missing HttpRequestThreadLocalFilter from the web.xml?"); //$NON-NLS-1$
            }
            builder.append(currentRequest.getScheme());
            builder.append("://"); //$NON-NLS-1$
            builder.append(currentRequest.getServerName());
            int serverPort = currentRequest.getServerPort();
            if (serverPort != 80 && serverPort != 443) {
                builder.append(":").append(serverPort); //$NON-NLS-1$
            }
            builder.append("/gateway/"); //$NON-NLS-1$
        } else {
            builder.append(endpoint);
            if (!endpoint.endsWith("/")) { //$NON-NLS-1$
                builder.append("/"); //$NON-NLS-1$
            }
        }
        return builder;
    }

}
