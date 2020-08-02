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

package io.apiman.gateway.api.rest.exceptions;

import io.apiman.gateway.engine.beans.exceptions.IStatusCode;

/**
 * Thrown when the user attempts to do or see something that they
 * are not authorized (do not have permission) to.
 *
 * @author eric.wittmann@redhat.com
 */
public class NotAuthorizedException extends AbstractRestException implements IStatusCode {

    private static final long serialVersionUID = 4663705773331595639L;
    private int statusCode = 403;

    /**
     * Constructor.
     * @param message the exception message
     */
    public NotAuthorizedException(String message) {
        super(message);
    }

    @Override
    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

}
