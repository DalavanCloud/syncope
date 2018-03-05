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
package org.apache.syncope.client.console.panels;

import java.util.ArrayList;
import java.util.List;
import org.apache.syncope.client.console.wicket.markup.html.bootstrap.tabs.Accordion;
import org.apache.syncope.common.lib.types.SchemaType;
import org.apache.wicket.PageReference;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class SchemasPanel extends Panel {

    private static final long serialVersionUID = -1140213992451232279L;

    private final PageReference pageReference;

    public SchemasPanel(final String id, final PageReference pageRef) {
        super(id);

        this.pageReference = pageRef;

        final Accordion accordion = new Accordion("accordionPanel", buildTabList());
        accordion.setOutputMarkupId(true);
        add(accordion);
    }

    private List<ITab> buildTabList() {

        final List<ITab> tabs = new ArrayList<>();

        for (final SchemaType schemaType : SchemaType.values()) {
            tabs.add(new AbstractTab(new Model<>(schemaType.name())) {

                private static final long serialVersionUID = 1037272333056449378L;

                @Override
                public Panel getPanel(final String panelId) {
                    return new SchemaTypePanelWithSearch(panelId, schemaType, pageReference);
                }
            });
        }
        return tabs;
    }
}
