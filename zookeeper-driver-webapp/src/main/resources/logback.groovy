import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import java.nio.charset.Charset

def env = System.properties['spring.profiles.active'] ?: 'production'
println '---log env---' + env

def appenderList = []
def level = ERROR

def LOG_RECEIVER_DIR = '/apps/logs/log_receiver/zookeeper-driver/framework'
def LOG_BUSINESS_DIR = '/apps/logs/log_receiver/zookeeper-driver/bussines'
def LOG_CONTROLLER_DIR = '/apps/logs/log_receiver/zookeeper-driver/controller'

if (env == 'production') {
    appenderList.add("ROLLING-ASYNC")
    level = WARN
} else if (env == 'development' || env == 'integrationtest') {
    appenderList.add("CONSOLE")
    level = DEBUG
}

if (env == 'development' || env == 'integrationtest') {
    appender("CONSOLE", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        }
    }
} else if (env == 'production') {
    appender("ROLLING", RollingFileAppender) {
        encoder(PatternLayoutEncoder) {
            Pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
            charset = Charset.forName("UTF-8")
        }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_RECEIVER_DIR}/translator-%d{yyyy-MM-dd-HH}.zip"
            timeBasedFileNamingAndTriggeringPolicy { maxFileSize = '10M' }
        }
    }
    appender("BUSSINES_ROLLING", RollingFileAppender) {
        encoder(PatternLayoutEncoder) {
            Pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
            charset = Charset.forName("UTF-8")
        }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_BUSINESS_DIR}/translator-%d{yyyy-MM-dd-HH}.zip"
            timeBasedFileNamingAndTriggeringPolicy { maxFileSize = '10M' }
        }
    }
    appender("CONTROLLER_ROLLING", RollingFileAppender) {
        encoder(PatternLayoutEncoder) {
            Pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
            charset = Charset.forName("UTF-8")
        }
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_CONTROLLER_DIR}/translator-%d{yyyy-MM-dd-HH}.zip"
            timeBasedFileNamingAndTriggeringPolicy { maxFileSize = '10M' }
        }
    }

    appender("ROLLING-ASYNC", AsyncAppender) {
        discardingThreshold = 0
        queueSize = 1024
        appenderRef("ROLLING")
        appenderRef("BUSSINES_ROLLING")
        appenderRef("CONTROLLER_ROLLING")
    }

    logger("org.sagesource.zookeeperdriver.service", INFO, ["BUSSINES_ROLLING"], false)
    logger("org.sagesource.zookeeperdriver.web.controller", INFO, ["CONTROLLER_ROLLING"], false)
    logger("org.sagesource.zookeeperdriver.client", DEBUG, ["ROLLING"], false)
}
root(level, appenderList)