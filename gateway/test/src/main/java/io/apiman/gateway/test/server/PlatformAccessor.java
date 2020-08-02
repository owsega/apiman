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
package io.apiman.gateway.test.server;

import io.apiman.gateway.api.rest.impl.IPlatform;
import io.apiman.gateway.api.rest.impl.IPlatformAccessor;
import io.apiman.gateway.engine.beans.ApiEndpoint;
import io.apiman.gateway.engine.beans.GatewayEndpoint;

/**
 * The test platform accessor.
 *
 * @author eric.wittmann@redhat.com
 */
public class PlatformAccessor implements IPlatformAccessor {

    private static final IPlatform platform = new IPlatform() {
        @SuppressWarnings("nls")
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

        public GatewayEndpoint getEndpoint() {
            GatewayEndpoint endpoint = new GatewayEndpoint();
            endpoint.setEndpoint(getGatewayEndpoint().toString());
            return endpoint;
        }

        private StringBuilder getGatewayEndpoint() {
            StringBuilder builder = new StringBuilder();
            builder.append("http://localhost:").append(GatewayServer.gatewayServer.getPort()).append("/gateway/");
            return  builder;
        }
    };

    /**
     * @see io.apiman.gateway.api.rest.impl.IPlatformAccessor#getPlatform()
     */
    @Override
    public IPlatform getPlatform() {
        return platform;
    }

}
