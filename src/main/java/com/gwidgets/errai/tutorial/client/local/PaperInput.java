/*
 * Copyright (C) 2016 Red Hat, Inc. and/or its affiliates.
 *
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
 */

package com.gwidgets.errai.tutorial.client.local;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.annotations.Element;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.common.client.ui.HasValue;

/**
 * <p>
 * A wrapper for the Polymer paper-input element.
 *
 * <p>
 * Implements {@link HasValue} with {@link JsProperty} methods so that Errai data-binding binds to the {@code value}
 * property of this element.
 *
 * @author Max Barkley <mbarkley@redhat.com>
 */
@Element("paper-input")
@JsType(isNative = true)
public interface PaperInput extends HTMLElement, HasValue<String> {

  @Override @JsProperty String getValue();
  @Override @JsProperty void setValue(String value);


}
