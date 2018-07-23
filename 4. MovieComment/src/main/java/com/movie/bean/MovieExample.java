package com.movie.bean;

import java.util.ArrayList;
import java.util.List;

public class MovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieExample() {
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

        public Criteria andMidIsNull() {
            addCriterion("mid is null");
            return (Criteria) this;
        }

        public Criteria andMidIsNotNull() {
            addCriterion("mid is not null");
            return (Criteria) this;
        }

        public Criteria andMidEqualTo(Integer value) {
            addCriterion("mid =", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotEqualTo(Integer value) {
            addCriterion("mid <>", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThan(Integer value) {
            addCriterion("mid >", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mid >=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThan(Integer value) {
            addCriterion("mid <", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidLessThanOrEqualTo(Integer value) {
            addCriterion("mid <=", value, "mid");
            return (Criteria) this;
        }

        public Criteria andMidIn(List<Integer> values) {
            addCriterion("mid in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotIn(List<Integer> values) {
            addCriterion("mid not in", values, "mid");
            return (Criteria) this;
        }

        public Criteria andMidBetween(Integer value1, Integer value2) {
            addCriterion("mid between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andMidNotBetween(Integer value1, Integer value2) {
            addCriterion("mid not between", value1, value2, "mid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andGenresIsNull() {
            addCriterion("genres is null");
            return (Criteria) this;
        }

        public Criteria andGenresIsNotNull() {
            addCriterion("genres is not null");
            return (Criteria) this;
        }

        public Criteria andGenresEqualTo(String value) {
            addCriterion("genres =", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotEqualTo(String value) {
            addCriterion("genres <>", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresGreaterThan(String value) {
            addCriterion("genres >", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresGreaterThanOrEqualTo(String value) {
            addCriterion("genres >=", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresLessThan(String value) {
            addCriterion("genres <", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresLessThanOrEqualTo(String value) {
            addCriterion("genres <=", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresLike(String value) {
            addCriterion("genres like", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotLike(String value) {
            addCriterion("genres not like", value, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresIn(List<String> values) {
            addCriterion("genres in", values, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotIn(List<String> values) {
            addCriterion("genres not in", values, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresBetween(String value1, String value2) {
            addCriterion("genres between", value1, value2, "genres");
            return (Criteria) this;
        }

        public Criteria andGenresNotBetween(String value1, String value2) {
            addCriterion("genres not between", value1, value2, "genres");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andCommentsCountIsNull() {
            addCriterion("comments_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentsCountIsNotNull() {
            addCriterion("comments_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsCountEqualTo(Integer value) {
            addCriterion("comments_count =", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountNotEqualTo(Integer value) {
            addCriterion("comments_count <>", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountGreaterThan(Integer value) {
            addCriterion("comments_count >", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comments_count >=", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountLessThan(Integer value) {
            addCriterion("comments_count <", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountLessThanOrEqualTo(Integer value) {
            addCriterion("comments_count <=", value, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountIn(List<Integer> values) {
            addCriterion("comments_count in", values, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountNotIn(List<Integer> values) {
            addCriterion("comments_count not in", values, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountBetween(Integer value1, Integer value2) {
            addCriterion("comments_count between", value1, value2, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andCommentsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("comments_count not between", value1, value2, "commentsCount");
            return (Criteria) this;
        }

        public Criteria andAverageIsNull() {
            addCriterion("average is null");
            return (Criteria) this;
        }

        public Criteria andAverageIsNotNull() {
            addCriterion("average is not null");
            return (Criteria) this;
        }

        public Criteria andAverageEqualTo(Float value) {
            addCriterion("average =", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotEqualTo(Float value) {
            addCriterion("average <>", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageGreaterThan(Float value) {
            addCriterion("average >", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageGreaterThanOrEqualTo(Float value) {
            addCriterion("average >=", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageLessThan(Float value) {
            addCriterion("average <", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageLessThanOrEqualTo(Float value) {
            addCriterion("average <=", value, "average");
            return (Criteria) this;
        }

        public Criteria andAverageIn(List<Float> values) {
            addCriterion("average in", values, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotIn(List<Float> values) {
            addCriterion("average not in", values, "average");
            return (Criteria) this;
        }

        public Criteria andAverageBetween(Float value1, Float value2) {
            addCriterion("average between", value1, value2, "average");
            return (Criteria) this;
        }

        public Criteria andAverageNotBetween(Float value1, Float value2) {
            addCriterion("average not between", value1, value2, "average");
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