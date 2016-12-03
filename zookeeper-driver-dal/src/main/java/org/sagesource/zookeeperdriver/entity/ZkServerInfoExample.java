package org.sagesource.zookeeperdriver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZkServerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZkServerInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andServersIsNull() {
            addCriterion("servers is null");
            return (Criteria) this;
        }

        public Criteria andServersIsNotNull() {
            addCriterion("servers is not null");
            return (Criteria) this;
        }

        public Criteria andServersEqualTo(String value) {
            addCriterion("servers =", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersNotEqualTo(String value) {
            addCriterion("servers <>", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersGreaterThan(String value) {
            addCriterion("servers >", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersGreaterThanOrEqualTo(String value) {
            addCriterion("servers >=", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersLessThan(String value) {
            addCriterion("servers <", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersLessThanOrEqualTo(String value) {
            addCriterion("servers <=", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersLike(String value) {
            addCriterion("servers like", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersNotLike(String value) {
            addCriterion("servers not like", value, "servers");
            return (Criteria) this;
        }

        public Criteria andServersIn(List<String> values) {
            addCriterion("servers in", values, "servers");
            return (Criteria) this;
        }

        public Criteria andServersNotIn(List<String> values) {
            addCriterion("servers not in", values, "servers");
            return (Criteria) this;
        }

        public Criteria andServersBetween(String value1, String value2) {
            addCriterion("servers between", value1, value2, "servers");
            return (Criteria) this;
        }

        public Criteria andServersNotBetween(String value1, String value2) {
            addCriterion("servers not between", value1, value2, "servers");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNull() {
            addCriterion("isUse is null");
            return (Criteria) this;
        }

        public Criteria andIsuseIsNotNull() {
            addCriterion("isUse is not null");
            return (Criteria) this;
        }

        public Criteria andIsuseEqualTo(String value) {
            addCriterion("isUse =", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotEqualTo(String value) {
            addCriterion("isUse <>", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThan(String value) {
            addCriterion("isUse >", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseGreaterThanOrEqualTo(String value) {
            addCriterion("isUse >=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThan(String value) {
            addCriterion("isUse <", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLessThanOrEqualTo(String value) {
            addCriterion("isUse <=", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseLike(String value) {
            addCriterion("isUse like", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotLike(String value) {
            addCriterion("isUse not like", value, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseIn(List<String> values) {
            addCriterion("isUse in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotIn(List<String> values) {
            addCriterion("isUse not in", values, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseBetween(String value1, String value2) {
            addCriterion("isUse between", value1, value2, "isuse");
            return (Criteria) this;
        }

        public Criteria andIsuseNotBetween(String value1, String value2) {
            addCriterion("isUse not between", value1, value2, "isuse");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeIsNull() {
            addCriterion("retry_sleep_time is null");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeIsNotNull() {
            addCriterion("retry_sleep_time is not null");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeEqualTo(Integer value) {
            addCriterion("retry_sleep_time =", value, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeNotEqualTo(Integer value) {
            addCriterion("retry_sleep_time <>", value, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeGreaterThan(Integer value) {
            addCriterion("retry_sleep_time >", value, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("retry_sleep_time >=", value, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeLessThan(Integer value) {
            addCriterion("retry_sleep_time <", value, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeLessThanOrEqualTo(Integer value) {
            addCriterion("retry_sleep_time <=", value, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeIn(List<Integer> values) {
            addCriterion("retry_sleep_time in", values, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeNotIn(List<Integer> values) {
            addCriterion("retry_sleep_time not in", values, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeBetween(Integer value1, Integer value2) {
            addCriterion("retry_sleep_time between", value1, value2, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetrySleepTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("retry_sleep_time not between", value1, value2, "retrySleepTime");
            return (Criteria) this;
        }

        public Criteria andRetryTimesIsNull() {
            addCriterion("retry_times is null");
            return (Criteria) this;
        }

        public Criteria andRetryTimesIsNotNull() {
            addCriterion("retry_times is not null");
            return (Criteria) this;
        }

        public Criteria andRetryTimesEqualTo(Integer value) {
            addCriterion("retry_times =", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesNotEqualTo(Integer value) {
            addCriterion("retry_times <>", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesGreaterThan(Integer value) {
            addCriterion("retry_times >", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("retry_times >=", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesLessThan(Integer value) {
            addCriterion("retry_times <", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesLessThanOrEqualTo(Integer value) {
            addCriterion("retry_times <=", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesIn(List<Integer> values) {
            addCriterion("retry_times in", values, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesNotIn(List<Integer> values) {
            addCriterion("retry_times not in", values, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesBetween(Integer value1, Integer value2) {
            addCriterion("retry_times between", value1, value2, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("retry_times not between", value1, value2, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutIsNull() {
            addCriterion("conn_timeout is null");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutIsNotNull() {
            addCriterion("conn_timeout is not null");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutEqualTo(Integer value) {
            addCriterion("conn_timeout =", value, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutNotEqualTo(Integer value) {
            addCriterion("conn_timeout <>", value, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutGreaterThan(Integer value) {
            addCriterion("conn_timeout >", value, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("conn_timeout >=", value, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutLessThan(Integer value) {
            addCriterion("conn_timeout <", value, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("conn_timeout <=", value, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutIn(List<Integer> values) {
            addCriterion("conn_timeout in", values, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutNotIn(List<Integer> values) {
            addCriterion("conn_timeout not in", values, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("conn_timeout between", value1, value2, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andConnTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("conn_timeout not between", value1, value2, "connTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutIsNull() {
            addCriterion("session_timeout is null");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutIsNotNull() {
            addCriterion("session_timeout is not null");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutEqualTo(Integer value) {
            addCriterion("session_timeout =", value, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutNotEqualTo(Integer value) {
            addCriterion("session_timeout <>", value, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutGreaterThan(Integer value) {
            addCriterion("session_timeout >", value, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("session_timeout >=", value, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutLessThan(Integer value) {
            addCriterion("session_timeout <", value, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("session_timeout <=", value, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutIn(List<Integer> values) {
            addCriterion("session_timeout in", values, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutNotIn(List<Integer> values) {
            addCriterion("session_timeout not in", values, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("session_timeout between", value1, value2, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andSessionTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("session_timeout not between", value1, value2, "sessionTimeout");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andServerDescIsNull() {
            addCriterion("server_desc is null");
            return (Criteria) this;
        }

        public Criteria andServerDescIsNotNull() {
            addCriterion("server_desc is not null");
            return (Criteria) this;
        }

        public Criteria andServerDescEqualTo(String value) {
            addCriterion("server_desc =", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescNotEqualTo(String value) {
            addCriterion("server_desc <>", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescGreaterThan(String value) {
            addCriterion("server_desc >", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescGreaterThanOrEqualTo(String value) {
            addCriterion("server_desc >=", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescLessThan(String value) {
            addCriterion("server_desc <", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescLessThanOrEqualTo(String value) {
            addCriterion("server_desc <=", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescLike(String value) {
            addCriterion("server_desc like", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescNotLike(String value) {
            addCriterion("server_desc not like", value, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescIn(List<String> values) {
            addCriterion("server_desc in", values, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescNotIn(List<String> values) {
            addCriterion("server_desc not in", values, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescBetween(String value1, String value2) {
            addCriterion("server_desc between", value1, value2, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andServerDescNotBetween(String value1, String value2) {
            addCriterion("server_desc not between", value1, value2, "serverDesc");
            return (Criteria) this;
        }

        public Criteria andClientKeyIsNull() {
            addCriterion("client_key is null");
            return (Criteria) this;
        }

        public Criteria andClientKeyIsNotNull() {
            addCriterion("client_key is not null");
            return (Criteria) this;
        }

        public Criteria andClientKeyEqualTo(String value) {
            addCriterion("client_key =", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyNotEqualTo(String value) {
            addCriterion("client_key <>", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyGreaterThan(String value) {
            addCriterion("client_key >", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyGreaterThanOrEqualTo(String value) {
            addCriterion("client_key >=", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyLessThan(String value) {
            addCriterion("client_key <", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyLessThanOrEqualTo(String value) {
            addCriterion("client_key <=", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyLike(String value) {
            addCriterion("client_key like", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyNotLike(String value) {
            addCriterion("client_key not like", value, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyIn(List<String> values) {
            addCriterion("client_key in", values, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyNotIn(List<String> values) {
            addCriterion("client_key not in", values, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyBetween(String value1, String value2) {
            addCriterion("client_key between", value1, value2, "clientKey");
            return (Criteria) this;
        }

        public Criteria andClientKeyNotBetween(String value1, String value2) {
            addCriterion("client_key not between", value1, value2, "clientKey");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}