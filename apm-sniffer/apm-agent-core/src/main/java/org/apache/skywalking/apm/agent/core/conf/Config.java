/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.skywalking.apm.agent.core.conf;

import org.apache.skywalking.apm.agent.core.context.trace.TraceSegment;
import org.apache.skywalking.apm.agent.core.logging.core.LogLevel;
import org.apache.skywalking.apm.agent.core.logging.core.WriterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the core config in sniffer agent.
 *
 * @author wusheng
 */
public class Config {

    public static class Agent {
        /**
         * Namespace isolates headers in cross process propagation. The HEADER name will be `HeaderName:Namespace`.
         */
        public static String NAMESPACE = "";

        /**
         * Service name is showed in skywalking-ui. Suggestion: set a unique name for each service,
         * service instance nodes share the same code
         */
        public static String SERVICE_NAME = "";

        /**
         * Authentication active is based on backend setting, see application.yml for more details.
         * For most scenarios, this needs backend extensions, only basic match auth provided in default implementation.
         */
        public static String AUTHENTICATION = "";

        /**
         * Negative or zero means off, by default. {@link #SAMPLE_N_PER_3_SECS} means sampling N {@link TraceSegment} in
         * 3 seconds tops.
         */
        public static int SAMPLE_N_PER_3_SECS = -1;

        /**
         * If the operation name of the first span is included in this set, this segment should be ignored.
         */
        public static String IGNORE_SUFFIX = ".jpg,.jpeg,.js,.css,.png,.bmp,.gif,.ico,.mp3,.mp4,.html,.svg";

        /**
         * The max number of spans in a single segment. Through this config item, skywalking keep your application
         * memory cost estimated.
         */
        public static int SPAN_LIMIT_PER_SEGMENT = 300;

        /**
         * If true, skywalking agent will save all instrumented classes files in `/debugging` folder.
         * Skywalking team may ask for these files in order to resolve compatible problem.
         */
        public static boolean IS_OPEN_DEBUGGING_CLASS = false;

        /**
         * Active V2 header in default
         */
        public static boolean ACTIVE_V2_HEADER = true;

        /**
         * Deactive V1 header in default
         */
        public static boolean ACTIVE_V1_HEADER = false;

        /**
         * The identify of the instance
         */
        public static String INSTANCE_UUID = "";
    }

    public static class Collector {
        /**
         * grpc channel status check interval
         */
        public static long GRPC_CHANNEL_CHECK_INTERVAL = 30;
        /**
         * service and endpoint registry check interval
         */
        public static long APP_AND_SERVICE_REGISTER_CHECK_INTERVAL = 3;
        /**
         * Collector skywalking trace receiver service addresses.
         */
        public static String BACKEND_SERVICE = "";
    }

    public static class Jvm {
        /**
         * The buffer size of collected JVM info.
         */
        public static int BUFFER_SIZE = 60 * 10;
    }

    public static class Buffer {
        public static int CHANNEL_SIZE = 5;

        public static int BUFFER_SIZE = 300;
    }

    public static class Dictionary {
        /**
         * The buffer size of application codes and peer
         */
        public static int SERVICE_CODE_BUFFER_SIZE = 10 * 10000;

        public static int ENDPOINT_NAME_BUFFER_SIZE = 1000 * 10000;
    }

    public static class Logging {
        /**
         * Log file name.
         */
        public static String FILE_NAME = "skywalking-api.log";

        /**
         * Log files directory. Default is blank string, means, use "system.out" to output logs.
         *
         * Ref to {@link WriterFactory#getLogWriter()}
         */
        public static String DIR = "";

        /**
         * The max size of log file. If the size is bigger than this, archive the current file, and write into a new
         * file.
         */
        public static int MAX_FILE_SIZE = 300 * 1024 * 1024;

        /**
         * The log level. Default is debug.
         */
        public static LogLevel LEVEL = LogLevel.DEBUG;
    }

    public static class Plugin {
        public static class MongoDB {
            /**
             * If true, trace all the parameters in MongoDB access, default is false. Only trace the operation, not include parameters.
             */
            public static boolean TRACE_PARAM = false;
        }

        public static class Elasticsearch {
            /**
             * If true, trace all the DSL(Domain Specific Language) in ElasticSearch access, default is false.
             */
            public static boolean TRACE_DSL = false;
        }

        public static class Customize {
            /**
             * Custom enhancement class configuration file path, recommended to use an absolute path.
             */
            public static String ENHANCE_FILE = "";

            /**
             * Some information after custom enhancements, this configuration is used by the custom enhancement plugin.
             * And using Map CONTEXT for avoiding classloader isolation issue.
             */
            public static Map<String, Object> CONTEXT = new HashMap<String, Object>();
        }

        public static class SpringMVC {
            /**
             * If true, the fully qualified method name will be used as the endpoint name instead of the request URL, default is false.
             */
            public static boolean USE_QUALIFIED_NAME_AS_ENDPOINT_NAME = false;
        }

        public static class Toolkit {
            /**
             * If true, the fully qualified method name will be used as the operation name instead of the given operation name, default is false.
             */
            public static boolean USE_QUALIFIED_NAME_AS_OPERATION_NAME = false;
        }

        public static class MySQL {
            /**
             * If set to true, the parameters of the sql (typically {@link java.sql.PreparedStatement})
             * would be collected.
             */
            public static boolean TRACE_SQL_PARAMETERS = false;
            /**
             * For the sake of performance, SkyWalking won't save the entire parameters string into the tag,
             * but only the first {@code SQL_PARAMETERS_MAX_LENGTH} characters.
             *
             * Set a negative number to save the complete parameter string to the tag.
             */
            public static int SQL_PARAMETERS_MAX_LENGTH = 512;
        }

        public static class SolrJ {
            /**
             * If true, trace all the query parameters(include deleteByIds and deleteByQuery) in Solr query request, default is false.
             */
            public static boolean TRACE_STATEMENT = false;

            /**
             * If true, trace all the operation parameters in Solr request, default is false.
             */
            public static boolean TRACE_OPS_PARAMS = false;
        }

    }
}
