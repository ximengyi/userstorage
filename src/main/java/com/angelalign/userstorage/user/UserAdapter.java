/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.angelalign.userstorage.user;

import com.angelalign.userstorage.entity.UserEntity;
import org.jboss.logging.Logger;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserAdapter extends AbstractUserAdapterFederatedStorage {
    private static final Logger logger = Logger.getLogger(UserAdapter.class);
    protected UserEntity entity;
    protected String keycloakId;

    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, UserEntity entity) {
        super(session, realm, model);
        this.entity = entity;
        keycloakId = StorageId.keycloakId(model, String.valueOf(entity.getAccountId()));
    }

    public String getPassword() {
        return entity.getPassword();
    }

    public void setPassword(String password) {
        entity.setPassword(password);
    }

    @Override
    public String getUsername() {
        return entity.getUsername();
    }

    @Override
    public void setUsername(String username) {
        entity.setUsername(username);

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public String getEmail() {
        return "1203669692@qq.com";
    }

    @Override
    public String getId() {
        return keycloakId;
    }

    @Override
    public void setSingleAttribute(String name, String value) {

        if (name.equals("site")) {
            entity.setSite(value);
        } else {
            super.setSingleAttribute(name, value);
        }

    }

    @Override
    public void removeAttribute(String name) {

        if (name.equals("site")) {
            entity.setSite(null);
        } else {
            super.removeAttribute(name);
        }

    }

    @Override
    public void setAttribute(String name, List<String> values) {
        if (name.equals("username")) {
            entity.setUsername(values.get(0));
        } else if (name.equals("site")) {
             entity.setSite(values.get(0));
        } else {
            super.setAttribute(name, values);
        }
    }

    @Override
    public String getFirstAttribute(String name) {
        if (name.equals("username")) {
            return entity.getUsername();
        } else if (name.equals("site")) {
            return entity.getSite();
        } else {
            return super.getFirstAttribute(name);
        }


    }

    @Override
    public Map<String, List<String>> getAttributes() {
        Map<String, List<String>> attrs = super.getAttributes();
        MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
        all.putAll(attrs);
        all.add("site",entity.getSite());
        return all;
    }

    @Override
    public Stream<String> getAttributeStream(String name) {
        if (name.equals("site")) {
            return Stream.of(entity.getSite());
        } else {
            return super.getAttributeStream(name);
        }

    }

    public void setSite(String site) {
        entity.setSite(site);
    }


    public String getSite() {
        return entity.getSite();
    }
}
