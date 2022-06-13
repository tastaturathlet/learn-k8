/*
 * Copyright 2016-2019 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
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
package de.tastaturathlet.document.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

  private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

  private HeaderUtil() {
  }

  /**
   * <p>createAlert.</p>
   *
   * @param applicationName a {@link java.lang.String} object.
   * @param message         a {@link java.lang.String} object.
   * @param param           a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createAlert(String applicationName, String message, String param) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-" + applicationName + "-alert", message);
    headers.add("X-" + applicationName + "-params", param);
    return headers;
  }

  /**
   * <p>createEntityCreationAlert.</p>
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param param             a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createEntityCreationAlert(String applicationName,
      boolean enableTranslation, String entityName, String param) {
    String message = enableTranslation ? applicationName + "." + entityName + ".created"
        : "A new " + entityName + " is created with identifier " + param;
    return createAlert(applicationName, message, param);
  }

  /**
   * <p>createEntityUpdateAlert.</p>
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param param             a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createEntityUpdateAlert(String applicationName,
      boolean enableTranslation, String entityName, String param) {
    String message = enableTranslation ? applicationName + "." + entityName + ".updated"
        : "A " + entityName + " is updated with identifier " + param;
    return createAlert(applicationName, message, param);
  }

  /**
   * <p>createEntityDeletionAlert.</p>
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param param             a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createEntityDeletionAlert(String applicationName,
      boolean enableTranslation, String entityName, String param) {
    String message = enableTranslation ? applicationName + "." + entityName + ".deleted"
        : "A " + entityName + " is deleted with identifier " + param;
    return createAlert(applicationName, message, param);
  }

  /**
   * <p>createFailureAlert.</p>
   *
   * @param applicationName   a {@link java.lang.String} object.
   * @param enableTranslation a boolean.
   * @param entityName        a {@link java.lang.String} object.
   * @param errorKey          a {@link java.lang.String} object.
   * @param defaultMessage    a {@link java.lang.String} object.
   * @return a {@link org.springframework.http.HttpHeaders} object.
   */
  public static HttpHeaders createFailureAlert(String applicationName, boolean enableTranslation,
      String entityName, String errorKey, String defaultMessage) {
    log.error("Entity processing failed, {}", defaultMessage);

    String message = enableTranslation ? "error." + errorKey : defaultMessage;

    HttpHeaders headers = new HttpHeaders();
    headers.add("X-" + applicationName + "-error", message);
    headers.add("X-" + applicationName + "-params", entityName);
    return headers;
  }
}
