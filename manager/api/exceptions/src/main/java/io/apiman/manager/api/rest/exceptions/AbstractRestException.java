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

package io.apiman.manager.api.rest.exceptions;


/**
 * Base class for all APIMan errors coming out of the REST layer.
 *
 * @author eric.wittmann@redhat.com
 */
public abstract class AbstractRestException extends RuntimeException {

    private static final long serialVersionUID = -2406210413693314452L;
    
    // The stacktrace is only set on the client/UI side
    private transient String serverStack;
    
    /**
     * Constructor.
     */
    public AbstractRestException() {
    }
    
    /**
     * Constructor.
     * @param message the exception message
     */
    public AbstractRestException(String message) {
        super(message);
    }
    
    /**
     * Constructor.
     * @param cause the exception cause
     */
    public AbstractRestException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor.
     * @param message the exception message
     * @param cause the exception cause
     */
    public AbstractRestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the stacktrace
     */
    public String getServerStack() {
        return serverStack;
    }

    /**
     * @param stacktrace the stacktrace to set
     */
    public void setServerStack(String stacktrace) {
        this.serverStack = stacktrace;
    }

    /**
     * @return the httpCode
     */
    public abstract int getHttpCode();

    /**
     * @return the errorCode
     */
    public abstract int getErrorCode();

    /**
     * @return the moreInfo
     */
    public abstract String getMoreInfoUrl();

}
