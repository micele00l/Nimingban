/*
 * Copyright 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.nimingban.daogenerator;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class NMBDaoGenerator {

    private static final String PACKAGE = "com.hippo.nimingban.dao";
    private static final String OUT_DIR = "../app/src/main/java-gen";
    private static final String DELETE_DIR = "../app/src/main/java-gen/com/hippo/nimingban/dao";

    public static void generate() throws Exception {
        Utilities.deleteContents(new File(DELETE_DIR));
        File outDir = new File(OUT_DIR);
        outDir.delete();
        outDir.mkdirs();

        Schema schema = new Schema(1, PACKAGE);
        addACForum(schema);
        addDraft(schema);
        new DaoGenerator().generateAll(schema, OUT_DIR);
    }

    private static void addACForum(Schema schema) {
        Entity entity = schema.addEntity("ACForumRaw");
        entity.setTableName("AC_FORUM");
        entity.setClassNameDao("ACForumDao");
        entity.addIdProperty();
        entity.addStringProperty("forumid");
        entity.addStringProperty("displayname");
        entity.addIntProperty("priority");
        entity.addBooleanProperty("visibility");
    }

    private static void addDraft(Schema schema) {
        Entity entity = schema.addEntity("DraftRaw");
        entity.setTableName("DRAFT");
        entity.setClassNameDao("DraftDao");
        entity.addIdProperty();
        entity.addStringProperty("content");
        entity.addLongProperty("time");
    }
}
