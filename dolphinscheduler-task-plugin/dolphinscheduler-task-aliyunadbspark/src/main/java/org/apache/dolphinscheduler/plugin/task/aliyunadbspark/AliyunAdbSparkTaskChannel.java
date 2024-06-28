/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.plugin.task.aliyunadbspark;

import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.plugin.task.api.AbstractTask;
import org.apache.dolphinscheduler.plugin.task.api.TaskChannel;
import org.apache.dolphinscheduler.plugin.task.api.TaskExecutionContext;
import org.apache.dolphinscheduler.plugin.task.api.parameters.AbstractParameters;

public class AliyunAdbSparkTaskChannel implements TaskChannel {

    @Override
    public AbstractTask createTask(TaskExecutionContext taskRequest) {
        AliyunAdbSparkBaseParameters params = JSONUtils.parseObject(taskRequest.getTaskParams(),
                AliyunAdbSparkBaseParameters.class);
        assert params != null;
        if ("Batch".equals(params.getAppType())) {
            return new AliyunAdbSparkBatchTask(taskRequest);
        } else if ("SQL".equals(params.getAppType())) {
            return new AliyunAdbSparkSqlTask(taskRequest);
        } else {
            throw new IllegalArgumentException("Unsupported program type: " + params.getAppType());
        }
    }

    @Override
    public AbstractParameters parseParameters(String taskParams) {
        return JSONUtils.parseObject(taskParams, AliyunAdbSparkBaseParameters.class);
    }
}