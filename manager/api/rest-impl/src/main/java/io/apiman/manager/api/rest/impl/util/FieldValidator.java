/*
 * Copyright 2015 JBoss Inc
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
package io.apiman.manager.api.rest.impl.util;

import io.apiman.manager.api.rest.exceptions.InvalidNameException;
import io.apiman.manager.api.rest.exceptions.i18n.Messages;
import io.apiman.manager.api.rest.exceptions.util.ExceptionFactory;

import org.apache.commons.lang3.StringUtils;

/**
 * Validates the name of an entity.
 *
 * @author eric.wittmann@redhat.com
 */
public class FieldValidator {

    private FieldValidator() {
    }

    /**
     * Validates an entity name.
     * @param name
     * @throws InvalidNameException
     */
    public static void validateName(String name) throws InvalidNameException {
        if (StringUtils.isEmpty(name)) {
            throw ExceptionFactory.invalidNameException(Messages.i18n.format("FieldValidator.EmptyNameError")); //$NON-NLS-1$
        }
    }

    /**
     * Validates an version.
     * @param name
     * @throws InvalidNameException
     */
    public static void validateVersion(String name) throws InvalidNameException {
        if (StringUtils.isEmpty(name)) {
            throw ExceptionFactory.invalidVersionException(Messages.i18n.format("FieldValidator.EmptyVersionError")); //$NON-NLS-1$
        }
    }

}
